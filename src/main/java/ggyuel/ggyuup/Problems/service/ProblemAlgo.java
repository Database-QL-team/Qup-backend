package ggyuel.ggyuup.Problems.service;

import ggyuel.ggyuup.Problems.dto.ProblemResponseDTO;
import ggyuel.ggyuup.global.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 알고리즘 태그에 따라 문제를 가져오는 서비스 클래스입니다.
 */
public class ProblemAlgo {
    /**
     * 지정된 알고리즘 태그에 따라 문제 목록을 반환합니다.
     *
     * @param request 알고리즘 태그
     * @return 문제 목록을 포함하는 ArrayList 객체
     */
    public static ArrayList<ProblemResponseDTO.ProblemAlgoDTO> getProblemsByTag(String request) {
        try {
            // DB 연결
            Connection conn = DBConnection.getDbPool().getConnection();

            // 사용자가 입력한 알고리즘 태그 추출
            String whichTag = request;

            // 쿼리 작성
            String query = "SELECT p.problem_id, p.title, p.link, p.tier, p.solved_num, pa.algo_id " +
                    "FROM problems p JOIN proalgo pa ON p.problem_id = pa.problem_id " +
                    "WHERE pa.algo_id = ? "+
                    "ORDER BY p.solved_num DESC";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, whichTag);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<ProblemResponseDTO.ProblemAlgoDTO> result = new ArrayList<>();

            // 결과 처리
            while (rs.next()) {
                // 튜플에서 problemId, title, link, tier, solvedNum 추출
                int problemId = rs.getInt("problem_id");
                String title = rs.getString("title");
                String link = rs.getString("link");
                int tier = rs.getInt("tier");
                int solvedNum = rs.getInt("solved_num");
                String algoId = rs.getString("algo_id");

                ProblemResponseDTO.ProblemAlgoDTO problemAlgoDTO = new ProblemResponseDTO.ProblemAlgoDTO(problemId, title, link, tier, solvedNum, algoId);

                // 추출한 데이터로 ProblemAlgoDTO 객체 생성 및 각 DTO 객체 ArrayList에 추가
                result.add(problemAlgoDTO);
            }


            // 자원 해환
            rs.close();
            pstmt.close();
            conn.close();

            // 문제 배열 반환
            return result;
        } catch (SQLException e) {
            // 예외 처리
            System.out.println(e);
        }
        return null;
    }
}
