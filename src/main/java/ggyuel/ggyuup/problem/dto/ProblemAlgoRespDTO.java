package ggyuel.ggyuup.problem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProblemAlgoRespDTO {
    @JsonProperty
    int problemId; // 문제 아이디
    @JsonProperty
    String title; // 문제 제목
    @JsonProperty
    String link; // 문제 링크
    @JsonProperty
    int tier;
    @JsonProperty
    int solvedNum; // 해결한 사람 수(이화여대 제외)
    @JsonProperty
    String algoId; // 문제 아이디
}
