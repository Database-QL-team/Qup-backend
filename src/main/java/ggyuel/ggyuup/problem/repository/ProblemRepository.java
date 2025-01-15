package ggyuel.ggyuup.problem.repository;

import ggyuel.ggyuup.problem.dto.ProblemAlgoRespDTO;
import ggyuel.ggyuup.problem.dto.ProblemTierRespDTO;
import ggyuel.ggyuup.problem.mapper.ProblemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProblemRepository {
    private final ProblemMapper problemMapper;

    public List<ProblemAlgoRespDTO> selectProblemsByAlgo(String algoId) { return problemMapper.selectProblemsByAlgo(algoId); }
    public List<ProblemTierRespDTO> selectProblemsByTier(int tier) { return problemMapper.selectProblemsByTier(tier); }
}
