package ggyuel.ggyuup.global;
import com.zaxxer.hikari.HikariDataSource;
import ggyuel.ggyuup.dataCrawling.service.DataCrawlingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnection {
    private static HikariDataSource dbPool;
    private static final Logger log = LoggerFactory.getLogger(DataCrawlingServiceImpl.class);

    static {
        dbPool = new HikariDataSource();
        dbPool.setJdbcUrl("jdbc:mysql://localhost:3306/ggyuup");
        dbPool.setUsername("root");
        dbPool.setPassword("number5598");

        //dbPool.setJdbcUrl("jdbc:mysql://ewhabaekjoon.ctgkgsmgmdya.ap-northeast-2.rds.amazonaws.com:3306/ewhabaekjoon");
//        dbPool.setUsername("QLteam");
//        dbPool.setPassword("QLteam1234");

        // 추가 설정
        dbPool.setMinimumIdle(5); // 최소 유휴 상태의 커넥션 수
        dbPool.setMaximumPoolSize(20); // 풀의 최대 크기
        dbPool.setAutoCommit(true); // 자동 커밋 활성화
        dbPool.setPoolName("MyPool"); // 풀 이름
    }

    public static HikariDataSource getDbPool() {
        try {
            log.info("사용 중인 DB 커넥션 수: " + dbPool.getHikariPoolMXBean().getActiveConnections()+
                    "/n총 DB 커넥션 수: " + dbPool.getHikariPoolMXBean().getTotalConnections()+
                    "/nDB 커넥션 요청");
        }catch (Exception e) {log.info("DB풀 첫 생성");}
        return dbPool;
    }
}