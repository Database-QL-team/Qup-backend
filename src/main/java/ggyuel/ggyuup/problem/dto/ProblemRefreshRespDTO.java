package ggyuel.ggyuup.problem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProblemRefreshRespDTO {
    @JsonProperty
    private String handle;  // refresh한 사용자의 handle

    @JsonProperty
    private Boolean isFirstSolved;  // 최초 솔브한 문제가 있는지

    @JsonProperty
    private List<Integer> firstSolvedProblems;  // 최초 솔브한 문제 리스트
}
