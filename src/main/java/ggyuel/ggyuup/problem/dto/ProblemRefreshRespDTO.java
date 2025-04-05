package ggyuel.ggyuup.problem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProblemRefreshRespDTO {
    @JsonProperty
    private String handle;
    @JsonProperty
    private List<Integer> firstSolvedNums;
}
