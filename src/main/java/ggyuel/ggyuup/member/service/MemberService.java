package ggyuel.ggyuup.member.service;


import ggyuel.ggyuup.member.dto.MemberRankRespDTO;
import ggyuel.ggyuup.member.dto.MemberScoreRespDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MemberService {
    Boolean checkEwhain(String request);
    List<MemberRankRespDTO> selectMemberRank();
    MemberScoreRespDTO updateMemberScore(String handle, Set<Integer> newSolvedNums);
    float updateRare(String handle, Set<Integer> newSolvedNums);
    float updateBasic(String handle, Set<Integer> newSolvedNums);
    float selectRare(String handle);
    float selectBasic(String handle);
    Map<Integer, Integer> selectSolvedStu(String handle, Set<Integer> newSolvedNums);
    List<Integer> selectFirstSolve(Map<Integer, Integer> solvedStu);
}
