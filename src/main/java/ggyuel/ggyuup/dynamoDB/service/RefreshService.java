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

    public void removeAlreadySolvedAndSyncDB(String handle, ArrayList<Integer> solvedProblems){
        Semaphore semaphore = semaphoreMap.computeIfAbsent(handle, k -> new Semaphore(1));

        try {
            semaphore.acquire();
            Student student = studentRepository.getByHandle(handle);
            solvedProblems.removeAll(student.getSolvedProblems());
            if(solvedProblems.isEmpty())
            {
                semaphore.release();
                return;
            }
            studentRepository.addSolvedProblem(student, solvedProblems);
            for(int pid : solvedProblems) problemRepository.incrementSolvedStudents(pid);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("스레드가 인터럽트 되었습니다 : removeAlreadySolvedAndSyncDB");
        } finally {
            semaphore.release();
        }
    }
}
