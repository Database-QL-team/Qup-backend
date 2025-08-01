package ggyuel.ggyuup.ranking.service;

import ggyuel.ggyuup.problem.dto.ProblemRefreshRespDTO;
import ggyuel.ggyuup.ranking.dto.RankingRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface RankingService {
    public List<RankingRespDTO> getEwhaRank();
    public void refreshScores(ProblemRefreshRespDTO problemRefreshRespDTO);
    public float refreshBasic(String handle, List<Integer> updatedProblems);
    public float refreshRare(String handle, List<Integer> updatedProblems);
    public void updateRankingTable() throws InterruptedException;
    public float updateBasic(String handle, Map<Integer, Integer> rareScoreMap) throws InterruptedException;
    public float updateRare(String handle, Map<Integer, Float> rareScoreMap);
    public int selectTier(Integer pid);
}
