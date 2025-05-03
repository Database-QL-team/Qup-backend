package ggyuel.ggyuup.ranking.service;

import ggyuel.ggyuup.problem.dto.ProblemRefreshRespDTO;
import ggyuel.ggyuup.ranking.dto.RankingRespDTO;

import java.util.List;
import java.util.Set;

public class RankingServiceImpl implements RankingService {
    @Override
    public List<RankingRespDTO> getEwhaRank() {
        return null;
    }

    @Override
    public ProblemRefreshRespDTO refreshScores(Set<Integer> updatedProblems) {
        return null;
    }

    @Override
    public float refreshBasic(Set<Integer> updatedProblems) {
        return 0;
    }

    @Override
    public float refreshRare(Set<Integer> updatedProblems) {
        return 0;
    }

    @Override
    public void updateRankingTable() {

    }

    @Override
    public float updateBasic() {
        return 0;
    }

    @Override
    public float updateRare() {
        return 0;
    }
}
