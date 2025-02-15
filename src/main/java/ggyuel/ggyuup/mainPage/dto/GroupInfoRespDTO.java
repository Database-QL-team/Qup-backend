package ggyuel.ggyuup.mainPage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GroupInfoRespDTO {
    @JsonProperty
    int ewhaRank; // 이화여대 백준 랭킹
    @JsonProperty
    int rivalRank; // 전 순위 그룹의 백준 랭킹
    @JsonProperty
    String rivalName; // 전 순위 그룹의 이름
    @JsonProperty
    int solvedNumGap; // 전 순위 그룹과 푼 문제 수 차이
}
