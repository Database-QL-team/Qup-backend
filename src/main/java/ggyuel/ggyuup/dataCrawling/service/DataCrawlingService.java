package ggyuel.ggyuup.dataCrawling.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

public interface DataCrawlingService {
    void RefreshAllData() throws InterruptedException, IOException;
    void crawlProblems();
    void insertTodayPS(Connection conn);
    void crawlUser(String user);
    Set<Integer> userRefresh(String user);
    void crawlSchool();
    void getUsers();
    void crawlGroups();
    void verifySolvedNum(int solvedNum);
    void saveEwhaHistory();
    int getTodaySolvedNum();
    int getProblemTier(int num);
}
