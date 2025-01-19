package ggyuel.ggyuup.dataCrawling.service;

import java.io.IOException;
import java.sql.Connection;

public interface DataCrawlingService {
    void RefreshAllData() throws InterruptedException, IOException;
    void crawlProblems();
    void insertTodayPS(Connection conn);
    void crawlUser(String user);
    void userRefresh(String user);
    void crawlSchool();
    void getUsers();
    void crawlGroups();
    void verifySolvedNum(int solvedNum);

}
