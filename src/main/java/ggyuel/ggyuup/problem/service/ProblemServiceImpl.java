package ggyuel.ggyuup.problem.service;

import ggyuel.ggyuup.problem.dto.ProblemAlgoRespDTO;
import ggyuel.ggyuup.problem.dto.ProblemTierRespDTO;
import ggyuel.ggyuup.problem.mapper.ProblemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final ProblemMapper problemMapper;

    @Override
    public Page<ProblemAlgoRespDTO> getProblemsByAlgo(String algo, Pageable pageable) {
        List<ProblemAlgoRespDTO> content = problemMapper.selectProblemsByAlgo(algo, pageable);
        Long totalElements = problemMapper.selectTotalProblemCountByAlgo(algo);
        return new PageImpl<>(content, pageable, totalElements);
    }

    @Override
    public Page<ProblemTierRespDTO> getProblemsByTier(int tier, Pageable pageable) {
        List<ProblemTierRespDTO> content = problemMapper.selectProblemsByTier(tier, pageable);
        Long totalElements = problemMapper.selectTotalProblemCountByTier(tier);
        return new PageImpl<>(content, pageable, totalElements);
    }
}
