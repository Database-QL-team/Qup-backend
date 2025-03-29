package ggyuel.ggyuup.dynamoDB.repository;

import ggyuel.ggyuup.dynamoDB.model.Student;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Repository
public class StudentRepository {
    private final DynamoDbTable<Student> table;

    public StudentRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.table = enhancedClient.table("queup", TableSchema.fromBean(Student.class));
    }

    public void save(String handle, Set<Integer> solvedProblems) {
        Student student = new Student();
        student.setHandle(handle);
        student.setSolvedProblems(solvedProblems);
        table.putItem(student);
    }

    public Student getByHandle(String handle) {
        return table.getItem(r -> r.key(k -> k.partitionValue("Student").sortValue(handle)));
    }

    public Set<Integer> getSolvedProblems(String handle) {
        return getByHandle(handle).getSolvedProblems();
    }

    public void addSolvedProblem(Student student, ArrayList<Integer> problemNumber) {
        student.getSolvedProblems().addAll(problemNumber);
        save(student.getHandle(), student.getSolvedProblems());
    }
}
