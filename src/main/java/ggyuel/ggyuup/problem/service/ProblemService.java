package ggyuel.ggyuup.problem.service;

import ggyuel.ggyuup.problem.dto.ProblemAlgoRespDTO;
import ggyuel.ggyuup.problem.dto.ProblemTierRespDTO;

import java.util.List;

public interface ProblemService {
    List<ProblemAlgoRespDTO> getProblemsByAlgo(String algo);
    List<ProblemTierRespDTO> getProblemsByTier(int tier);
}
