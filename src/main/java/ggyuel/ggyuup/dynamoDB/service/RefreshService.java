package ggyuel.ggyuup.dynamoDB.service;

import ggyuel.ggyuup.dynamoDB.model.Student;
import ggyuel.ggyuup.dynamoDB.repository.ProblemRepository;
import ggyuel.ggyuup.dynamoDB.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Service
public class RefreshService {
    private final StudentRepository studentRepository;
    private final ProblemRepository problemRepository;
    private final ConcurrentHashMap<String, Semaphore> semaphoreMap = new ConcurrentHashMap<>();

    public RefreshService(StudentRepository studentRepository, ProblemRepository problemRepository) {
        this.studentRepository = studentRepository;
        this.problemRepository = problemRepository;
    }

    public void removeAlreadySolvedAndSyncDB(String handle, ArrayList<Integer> solvedProblems, ArrayList<Integer> firstSolvedProblems){
        Semaphore semaphore = semaphoreMap.computeIfAbsent(handle, k -> new Semaphore(1));

        try {
            semaphore.acquire();
            Student student = studentRepository.getByHandle(handle);

            // 새로 푼 문제만 걸러내기
            solvedProblems.removeAll(student.getSolvedProblems());
            if(solvedProblems.isEmpty())
            {
                semaphore.release();
                return;
            }

            // DB 업데이트
            studentRepository.addSolvedProblem(student, solvedProblems);
            for(int pid : solvedProblems) {
                if(problemRepository.incrementSolvedStudents(pid))
                    firstSolvedProblems.add(pid);  // 전체에서 최초 푼 문제면 추가
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("스레드가 인터럽트 되었습니다 : removeAlreadySolvedAndSyncDB");
        } finally {
            semaphore.release();
        }
    }
}
