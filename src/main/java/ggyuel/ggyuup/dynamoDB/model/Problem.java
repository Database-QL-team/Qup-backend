package ggyuel.ggyuup.dynamoDB.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.Set;

@DynamoDbBean
public class Problem {
    private String type = "Problem";  // 고정값
    private String number;
    private int solvedStudents;

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
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    @DynamoDbAttribute("SolvedStudents")
    public int getSolvedStudents() {
        return solvedStudents;
    }
    public void setSolvedStudents(int solvedStudents) {
        this.solvedStudents = solvedStudents;
    }
}
