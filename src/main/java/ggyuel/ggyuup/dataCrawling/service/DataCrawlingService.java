package ggyuel.ggyuup.dataCrawling.service;

import ggyuel.ggyuup.problem.dto.ProblemRefreshRespDTO;

import java.io.IOException;
import java.sql.Connection;

public interface DataCrawlingService {

    public int getSolvedStudents(int pid);
    void RefreshAllData() throws InterruptedException, IOException;
    void crawlProblems();
    void insertTodayPS(Connection conn);
    void crawlUser(String user);
    ProblemRefreshRespDTO userRefresh(String user);
    void crawlSchool();
    void getUsers();
    void crawlGroups();
    void verifySolvedNum(int solvedNum);
    void saveEwhaHistory();
    int getTodaySolvedNum();
}
