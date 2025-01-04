package ggyuel.ggyuup.global;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnection {
    private static HikariDataSource dbPool;

    static {
        dbPool = new HikariDataSource();
        dbPool.setJdbcUrl("jdbc:mysql://ewhabaekjoon.ctgkgsmgmdya.ap-northeast-2.rds.amazonaws.com:3306/ewhabaekjoon");
        dbPool.setUsername("QLteam");
        dbPool.setPassword("QLteam1234");

        // 추가 설정
        dbPool.setMinimumIdle(5); // 최소 유휴 상태의 커넥션 수
        dbPool.setMaximumPoolSize(20); // 풀의 최대 크기
        dbPool.setAutoCommit(true); // 자동 커밋 활성화
        dbPool.setPoolName("MyPool"); // 풀 이름
    }

    public static HikariDataSource getDbPool() {
        return dbPool;
    }
}