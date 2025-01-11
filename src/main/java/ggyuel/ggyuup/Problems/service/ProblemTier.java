package ggyuel.ggyuup.Problems.service;

import ggyuel.ggyuup.Problems.dto.ProblemResponseDTO;
import ggyuel.ggyuup.global.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 티어에 따라 문제를 가져오는 서비스 클래스입니다.
 */
public class ProblemTier {
    /**
     * 지정된 티어에 따라 문제 목록을 반환합니다.
     *
     * @param request 문제의 티어
     * @return 문제 목록을 포함하는 ArrayList 객체
     */
    public static ArrayList<ProblemResponseDTO.ProblemTierDTO> getProblemsByTier(int request) {
        try {
            // DB 연결
            Connection conn = DBConnection.getDbPool().getConnection();

            // 요청된 티어
            int whichTier = request;

            String query = "SELECT problem_id, title, link, tier, solved_num " +
                    "FROM problems " +
                    "WHERE tier = ? " +
                    "ORDER BY solved_num DESC";

            PreparedStatement pstmt = conn.prepareStatement(query);

            // 티어 매개변수를 설정
            pstmt.setInt(1, whichTier);
            ResultSet rs = pstmt.executeQuery();


            // 티어별 문제 목록을 저장할 ArrayList 생성
            ArrayList<ProblemResponseDTO.ProblemTierDTO> tierProblems = new ArrayList<>();

            // 결과 처리
            while (rs.next()) {
                // 튜플에서 title, tier, link, solvednum 추출 후 ProblemTierDTO 객체 생성 후 ArrayList에 삽입
                int problemId = rs.getInt("problem_id");
                String title = rs.getString("title");
                String link = rs.getString("link");
                int tier = rs.getInt("tier");
                int solvedNum= rs.getInt("solved_num");

                tierProblems.add(new ProblemResponseDTO.ProblemTierDTO(problemId, title, link, tier, solvedNum));
            }

            // 자원 해제
            rs.close();
            pstmt.close();
            conn.close();

            // 문제 배열 반환
            return tierProblems;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
}

