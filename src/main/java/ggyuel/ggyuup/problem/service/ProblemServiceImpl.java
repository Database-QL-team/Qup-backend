package ggyuel.ggyuup.problem.service;

import ggyuel.ggyuup.problem.dto.ProblemAlgoRespDTO;
import ggyuel.ggyuup.problem.dto.ProblemTierRespDTO;
import ggyuel.ggyuup.problem.mapper.ProblemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public List<ProblemAlgoRespDTO> getProblemsByAlgo(String algo) {
        List<ProblemAlgoRespDTO> problemAlgoDTOList = problemMapper.selectProblemsByAlgo(algo);
        return problemAlgoDTOList;
    }

    @Override
    public List<ProblemTierRespDTO> getProblemsByTier(int tier) {
        List<ProblemTierRespDTO> problemTierDTOList = problemMapper.selectProblemsByTier(tier);
        return problemTierDTOList;

    }
}
