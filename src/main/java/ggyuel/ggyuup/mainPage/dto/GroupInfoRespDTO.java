package ggyuel.ggyuup.mainPage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupInfoRespDTO {
    @JsonProperty
    int ewhaRank; // 이화여대 백준 랭킹
    @JsonProperty
    int rivalRank; // 전 순위 그룹의 백준 랭킹
    @JsonProperty
    String rivalName; // 전 순위 그룹의 이름
    @JsonProperty
    int solvedNumGap; // 전 순위 그룹과 푼 문제 수 차이

    /**
     * GroupInfoDTO 생성자.
     *
     * @param ewhaRank       이화 랭킹
     * @param rivalRank      전 순위 그룹의 백준 랭킹
     * @param rivalName      전 순위 그룹의 이름
     * @param solvedNumGap     전 순위 그룹과 푼 문제 수 차이
     */
    public GroupInfoRespDTO (@JsonProperty("ewhaRank") int ewhaRank,
                         @JsonProperty("rivalRank") int rivalRank,
                         @JsonProperty("rivalName") String rivalName,
                         @JsonProperty("solvedNumGap") int solvedNumGap) {
        this.ewhaRank = ewhaRank;
        this.rivalRank = rivalRank;
        this.rivalName = rivalName;
        this.solvedNumGap = solvedNumGap;
    }
}
