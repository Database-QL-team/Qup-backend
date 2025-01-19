package ggyuel.ggyuup.mainPage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodayPsRespDTO {
    @JsonProperty
    int problemId;
    @JsonProperty
    String title; // 문제 제목
    @JsonProperty
    String link;
    @JsonProperty
    int tier; // 문제 티어
    @JsonProperty
    int solvedNum;

    /**
     * TodayPSDTO 생성자.
     * @param problemId     문제 아이디
     * @param link     문제 링크
     * @param title    문제 제목
     * @param tier     문제 티어
     * @param solvedNum 푼 사람 수
     */
    public TodayPsRespDTO(@JsonProperty("problemId") int problemId,
                      @JsonProperty("title") String title,
                      @JsonProperty("link") String link,
                      @JsonProperty("tier") int tier,
                      @JsonProperty("solvedNum") int solvedNum) {
        this.problemId = problemId;
        this.title = title;
        this.link = link;
        this.tier = tier;
        this.solvedNum = solvedNum;
    }
}
