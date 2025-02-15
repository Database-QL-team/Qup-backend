package ggyuel.ggyuup.mainPage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TodayPsRespDTO {
    @JsonProperty
    int problemId; // 문제 아이디
    @JsonProperty
    String title; // 문제 제목
    @JsonProperty
    String link; // 문제 백준 링크
    @JsonProperty
    int tier; // 문제 티어
    @JsonProperty
    int solvedNum; // 문제 푼 사람 수
}
