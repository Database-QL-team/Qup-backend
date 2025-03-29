package ggyuel.ggyuup.dynamoDB.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.Set;

@DynamoDbBean
public class Student {
    private String type = "Student";
    private String handle;
    private Set<Integer> solvedProblems;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("Type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("Id")
    public String getHandle() {
        return handle;
    }
    public void setHandle(String handle) {
        this.handle = handle;
    }

    @DynamoDbAttribute("SolvedProblems")
    public Set<Integer> getSolvedProblems() {
        return solvedProblems;
    }
    public void setSolvedProblems(Set<Integer> solvedProblems) {
        this.solvedProblems = solvedProblems;
    }
}
