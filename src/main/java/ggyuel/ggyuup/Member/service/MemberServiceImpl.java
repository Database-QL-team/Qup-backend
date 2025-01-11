package ggyuel.ggyuup.Member.service;

import ggyuel.ggyuup.Member.dto.LoginDTO;
import ggyuel.ggyuup.global.DBConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Override
    public Boolean checkEwhain(LoginDTO request) {

        Boolean isExist = false;

        // 이화여대 학생의 핸들이 맞는지 확인
        try {
            String handle = request.getHandle();

            // DB 연결
            Connection conn = DBConnection.getDbPool().getConnection();

            // 쿼리 작성
            String query = "SELECT EXISTS ( " +
                    "SELECT 1 " +
                    "FROM students " +
                    "WHERE handle = ?) " +
                    "AS isExist";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, handle);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                isExist = rs.getBoolean("isExist");
            }

            // 자원 해환
            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            // 예외 처리
            System.out.println(e);
        }

        return isExist;
    }
}
