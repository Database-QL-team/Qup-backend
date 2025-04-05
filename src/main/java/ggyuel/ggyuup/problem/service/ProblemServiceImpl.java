package ggyuel.ggyuup.problem.service;

import ggyuel.ggyuup.dataCrawling.service.DataCrawlingService;
import ggyuel.ggyuup.member.service.MemberService;
import ggyuel.ggyuup.problem.dto.ProblemAlgoRespDTO;
import ggyuel.ggyuup.problem.dto.ProblemRefreshRespDTO;
import ggyuel.ggyuup.problem.dto.ProblemTierRespDTO;
import ggyuel.ggyuup.problem.event.ProblemRefreshedEvent;
import ggyuel.ggyuup.problem.mapper.ProblemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final ProblemMapper problemMapper;
    private final DataCrawlingService dataCrawlingService;
    private final MemberService memberService;
    private final ApplicationEventPublisher eventPublisher;

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

    @Override
    public ProblemRefreshRespDTO refreshProblems(String handle) {
        Set<Integer> newSolvedNums = dataCrawlingService.userRefresh(handle);
        Map<Integer, Integer> solvedStu = memberService.selectSolvedStu(handle, newSolvedNums);
        List<Integer> firstSolveList = memberService.selectFirstSolve(solvedStu);

        //이벤트 발행
        eventPublisher.publishEvent(new ProblemRefreshedEvent(handle, newSolvedNums));

        ProblemRefreshRespDTO problemRefreshRespDTO = ProblemRefreshRespDTO.builder()
                .firstSolvedNums(firstSolveList)
                .handle(handle)
                .build();

        return problemRefreshRespDTO;
    }
}
