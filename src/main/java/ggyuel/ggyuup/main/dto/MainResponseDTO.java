package ggyuel.ggyuup.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * MainResponseDTO 클래스는 메인 페이지 응답 데이터를 표현합니다.
 */
public class MainResponseDTO {

    /**
     * GroupInfoDTO 클래스는 그룹의 랭킹 정보를 나타냅니다.
     * 그룹의 랭킹 정보란 이화여대의 백준 랭킹
     * 이화여대 전 순위 그룹의 이름, 전 순위 그룹과의 푼 문제 수 차이를 의미합니다.
     */
    public static class GroupInfoDTO {
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
        public GroupInfoDTO (@JsonProperty("ewhaRank") int ewhaRank,
                             @JsonProperty("rivalRank") int rivalRank,
                             @JsonProperty("rivalName") String rivalName,
                             @JsonProperty("solvedNumGap") int solvedNumGap) {
            this.ewhaRank = ewhaRank;
            this.rivalRank = rivalRank;
            this.rivalName = rivalName;
            this.solvedNumGap = solvedNumGap;
        }
    }

    /**
     * TodayPSDTO 클래스는 오늘의 문제 정보를 나타냅니다.
     */
    public static class TodayPSDTO {
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
        public TodayPSDTO(@JsonProperty("problemId") int problemId,
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

    /**
     * MainPageDTO 클래스는 메인 페이지의 정보를 포함합니다.
     * 메인페이지는 그룹의 랭킹 정보와 오늘의 문제 정보를 포함합니다.
     */
    public static class MainPageDTO {
        private GroupInfoDTO groupInfo; // 그룹 정보
        private ArrayList<TodayPSDTO> todayPSList; // 오늘의 문제 리스트

        public MainPageDTO(@JsonProperty("groupInfo") GroupInfoDTO groupInfo,
                           @JsonProperty("todayPSList") ArrayList<TodayPSDTO> todayPSList) {
            this.groupInfo = groupInfo;
            this.todayPSList = todayPSList;
        }

    }
}

