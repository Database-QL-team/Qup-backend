package ggyuel.ggyuup.dynamoDB.repository;

import ggyuel.ggyuup.dynamoDB.model.Problem;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Repository
public class ProblemRepository {
    private final DynamoDbTable<Problem> table;
    private final ConcurrentHashMap<Integer, Semaphore> semaphoreMap = new ConcurrentHashMap<>();

    public ProblemRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.table = enhancedClient.table("queup", TableSchema.fromBean(Problem.class));
    }

    public void save(int number, int solvedStudents) {
        Problem problem = new Problem();
        problem.setNumber(Integer.toString(number));
        problem.setSolvedStudents(solvedStudents);
        table.putItem(problem);
    }
    public void save(String number, int solvedStudents) {
        Problem problem = new Problem();
        problem.setNumber(number);
        problem.setSolvedStudents(solvedStudents);
        table.putItem(problem);
    }

    public Problem getByNumber(Integer number) {
        return table.getItem(r -> r.key(k -> k.partitionValue("Problem").sortValue(number.toString())));
    }

    public void incrementSolvedStudents(int number) {
        Semaphore semaphore = semaphoreMap.computeIfAbsent(number, k -> new Semaphore(1));  // 세마포어가 없으면 생성
        try {
            semaphore.acquire();  // 세마포어 요청
            Problem problem = getByNumber(number);
            if (problem != null) {
                save(problem.getNumber(),problem.getSolvedStudents() + 1);
            } else {
                save(number, 1);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("스레드가 인터럽트 되었습니다 : incrementSolvedStudents");
        } finally {
            semaphore.release();
        }

    }
}
