package ggyuel.ggyuup.ranking.service;

import ggyuel.ggyuup.problem.dto.ProblemRefreshRespDTO;
import ggyuel.ggyuup.ranking.dto.RankingRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface RankingService {
    public List<RankingRespDTO> getEwhaRank();
    public ProblemRefreshRespDTO refreshScores(Set<Integer> updatedProblems);
    public float refreshBasic(Set<Integer> updatedProblems);
    public float refreshRare(Set<Integer> updatedProblems);
    public void updateRankingTable();
    public float updateBasic();
    public float updateRare();
}
