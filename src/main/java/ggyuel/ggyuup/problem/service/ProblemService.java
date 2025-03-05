package ggyuel.ggyuup.problem.service;

import ggyuel.ggyuup.problem.dto.ProblemAlgoRespDTO;
import ggyuel.ggyuup.problem.dto.ProblemTierRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProblemService {
    Page<ProblemAlgoRespDTO> getProblemsByAlgo(String algo, Pageable pageable);
    Page<ProblemTierRespDTO> getProblemsByTier(int tier, Pageable pageable);
}
