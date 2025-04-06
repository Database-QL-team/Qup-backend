package ggyuel.ggyuup.member.service;

import ggyuel.ggyuup.dataCrawling.service.DataCrawlingService;
import ggyuel.ggyuup.dynamoDB.repository.ProblemRepository;
import ggyuel.ggyuup.member.dto.MemberRankRespDTO;
import ggyuel.ggyuup.member.dto.MemberScoreRespDTO;
import ggyuel.ggyuup.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final ProblemRepository problemRepository;
    private final DataCrawlingService dataCrawlingService;

    @Override
    public Boolean checkEwhain(String request) {
        String handle = request;
        return memberMapper.checkEwhain(handle);
    }

    @Override
    public List<MemberRankRespDTO> selectMemberRank() {
        List<MemberRankRespDTO> sortedMemberRankDTOs = memberMapper.selectMemberRank();
        List<MemberRankRespDTO> rankedMemberRankDTO = new ArrayList<>();

        int rank = 1;
        int prevTotal = -1;
        int actualRank = 1;

        for (int i = 0; i < sortedMemberRankDTOs.size(); i++){
            MemberRankRespDTO dto = sortedMemberRankDTOs.get(i);
            int total = dto.getTotal();

            if(total != prevTotal){
                rank = actualRank;
            }

            rankedMemberRankDTO.add(MemberRankRespDTO.builder()
                            .handle(dto.getHandle())
                            .total(dto.getTotal())
                            .rank(dto.getRank())
                    .build());

            prevTotal = total;
            actualRank++;
        }

        return rankedMemberRankDTO;
    }

    @Override
    public MemberScoreRespDTO updateMemberScore(String handle, Set<Integer> newSolvedNums) {
        // update할 rare값 불러오기
        float rare = updateRare(handle, newSolvedNums);

        // update할 basic값 불러오기
        float basic = updateBasic(handle, newSolvedNums);

        // DB에 업데이트(rare, basic, total)
        memberMapper.updateMemberScore(handle, basic, rare);

        // DTO 생성해서 반환
        MemberScoreRespDTO memberScoreRespDTO = MemberScoreRespDTO.builder()
                .handle(handle)
                .rare(memberMapper.getMemberRare(handle))
                .basic(memberMapper.getMemberBasic(handle))
                .total(memberMapper.getMemberTotal(handle))
                .build();

        return memberScoreRespDTO;
    }



    @Override
    public float updateRare(String handle, Set<Integer> newSolvedNums) {
        float addScore = 0;

        // DB에 저장되어 있던 rare값 가져오기
        float currRare = selectRare(handle);

        // 번호, 푼 사람 수 Map 가져오기
        Map<Integer, Integer> solvedStuList = selectSolvedStu(handle, newSolvedNums);
        Set<Integer> keys = solvedStuList.keySet();
        for(Integer key : keys){
            addScore += 10 * Math.exp(-0.02 * solvedStuList.get(key));
        }

        return currRare + addScore;
    }

    @Override
    public float updateBasic(String handle, Set<Integer> newSolvedNums) {
        float addScore = 0;

        // DB에 저장되어 있던 basic값 가져오기
        float currBasic = selectBasic(handle);

        // 새롭게 푼 문제의 난이도 가져오기
        for(int num : newSolvedNums) {
            // solved.ac API로 난이도 불러오기
            int tier = dataCrawlingService.getProblemTier(num);
            addScore += tier;
        }

        return currBasic + addScore;

    }

    @Override
    public float selectRare(String handle) {
        return memberMapper.getMemberRare(handle);
    }

    @Override
    public float selectBasic(String handle) {
        return memberMapper.getMemberBasic(handle);
    }

    @Override
    public Map<Integer, Integer> selectSolvedStu(String handle, Set<Integer> newSolvedNums) {
        Map<Integer, Integer> solvedStuList = new HashMap<>();
        for(int num : newSolvedNums) {
            int solvedPeople = problemRepository.incrementSolvedStudents(num);
            solvedStuList.put(num, solvedPeople);
        }
        return solvedStuList;
    }

    @Override
    public List<Integer> selectFirstSolve(Map<Integer, Integer> solvedStu) {
        List<Integer> firstSolveList = new ArrayList<>();

        Set<Integer> keys = solvedStu.keySet();
        for(Integer key : keys) {
            if(solvedStu.get(key) == 1) {
                firstSolveList.add(key);
            }
        }

        return firstSolveList;
    }

}
