package ggyuel.ggyuup.mainPage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Builder
@Getter
public class MainPageRespDTO {
    @JsonProperty
    private GroupInfoRespDTO groupInfo; // 그룹 정보
    @JsonProperty
    private ArrayList<TodayPsRespDTO> todayPSList; // 오늘의 문제 리스트
}
