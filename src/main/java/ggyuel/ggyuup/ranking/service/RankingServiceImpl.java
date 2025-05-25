package ggyuel.ggyuup.ranking.service;

import ggyuel.ggyuup.dataCrawling.service.DataCrawlingService;
import ggyuel.ggyuup.dynamoDB.repository.StudentRepository;
import ggyuel.ggyuup.member.mapper.MemberMapper;
import ggyuel.ggyuup.problem.dto.ProblemRefreshRespDTO;
import ggyuel.ggyuup.problem.mapper.ProblemMapper;
import ggyuel.ggyuup.ranking.dto.RankingRespDTO;
import ggyuel.ggyuup.ranking.dto.UserLevelStatRespDTO;
import ggyuel.ggyuup.ranking.dto.UserProblemStatsRespDTO;
import ggyuel.ggyuup.ranking.mapper.RankingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

public class RankingServiceImpl implements RankingService {

    @Autowired
    private RankingMapper rankingMapper;
    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DataCrawlingService dataCrawlingService;

    private final RestTemplate restTemplate;

    public RankingServiceImpl(){
        this.restTemplate = new RestTemplate();
    }

    // 이화 기여도 ranking 조회
    @Override
    public List<RankingRespDTO> getEwhaRank() {
        List<RankingRespDTO> rankingRespDTOList = rankingMapper.selectEwhaRank();
        return rankingRespDTOList;
    }

    // refresh 버튼 눌렀을 때 basic, rare, total 점수 업데이트
    @Override
    public void refreshScores(ProblemRefreshRespDTO problemRefreshRespDTO) {
        // refresh할 사용자 handle get
        String handle = problemRefreshRespDTO.getHandle();

        // update된 문제 번호 get
        List<Integer> updatedProblems = problemRefreshRespDTO.getNewSolvedProblems();

        // refreshBasic 호출 - refresh될 basic 점수 get
        float updatedBasic = refreshBasic(handle, updatedProblems);

        // refreshRare 호출 - refresh될 rare 점수 get
        float updatedRare = refreshRare(handle, updatedProblems);

        // total 점수 계산
        float updatedTotal = updatedBasic + updatedRare;

        // 점수들 db에 업데이트
        rankingMapper.refreshScores(handle, updatedTotal, updatedBasic, updatedRare);
    }

    // refresh 버튼 - basic 업데이트
    @Override
    public float refreshBasic(String handle, List<Integer> updatedProblems) {
        // plus해줄 basic 점수 계산
        float addBasic = 0;
        for (int pid : updatedProblems) {
            Integer tier = problemMapper.selectTier(pid);

            // 아직 DB 업데이트가 완료되지 못했을 경우 - solved.ac API 사용
            if(tier == null) {
                String url = UriComponentsBuilder
                        .fromHttpUrl("https://solved.ac/api/v3/problem/show")
                        .queryParam("problemId", pid)
                        .toUriString();

                Map<String, Object> response = restTemplate.getForObject(url, Map.class);
                Object level = response.get("level");

                tier = (Integer)level;
            }

            addBasic += tier;
        }

        // 기존 basic 점수에 plus해서 반환
        return rankingMapper.selectBasic(handle) + addBasic;
    }

    // refresh 버튼 - rare 업데이트
    @Override
    public float refreshRare(String handle, List<Integer> updatedProblems) {
        // plus할 rare 점수 계산
        float addRare = 0;

        for(int pid : updatedProblems){
            int solvedStudents = dataCrawlingService.getSolvedStudents(pid);
            addRare += 10 * Math.exp(-0.02 * solvedStudents);
        }

        // 기존 rare 점수에 plus해서 반환
        return rankingMapper.selectRare(handle) + addRare;
    }

    // ranking table 정기 갱신(하루 한번)
    @Override
    @Scheduled(cron = "00 30 21 * * ?")
    public void updateRankingTable() {
        // 기존 table의 data delete
        rankingMapper.deleteScores();

        // 이화여대 학생 목록 불러오기
        List<String> handleList = memberMapper.selectEwhain();

        // rare 점수 중복 계산 방지 위한 문제별 rare값 저장소
        Map<Integer, Float> rareScoreMap = new HashMap<>();

        for (String handle : handleList) {
            // updateBasic 호출해서 insert할 basic 점수 get
            float insertBasic = updateBasic(handle);

            // updateRare 호출해서 insert할 rare 점수 get
            float insertRare = updateRare(handle, rareScoreMap);

            // insert할 total 점수 계산
            float insertTotal = insertBasic + insertRare;

            // basic, rare, total 점수 insert
            rankingMapper.insertScores(handle, insertTotal, insertBasic, insertRare);
        }
    }

    // ranking table 정기 갱신 - basic 업데이트
    @Override
    public float updateBasic(String handle) {
        float insertBasic = 0;

        String url = "https://solved.ac/api/v3/user/problem_stats?handle=" + handle;
        UserProblemStatsRespDTO response = restTemplate.getForObject(url, UserProblemStatsRespDTO.class);

        List<UserLevelStatRespDTO> UserLevelStatDTOs = response.getItems();

        for(UserLevelStatRespDTO userLevelStat : UserLevelStatDTOs) {
            insertBasic += (userLevelStat.getLevel()) * (userLevelStat.getSolved());
        }

        return insertBasic;
    }

    // ranking table 정기 갱신 - rare 업데이트
    @Override
    public float updateRare(String handle, Map<Integer, Float> rareScoreMap) {
        float insertRare = 0;

        // 학생이 푼 문제 리스트 불러오기
        Set<Integer> solvedProblems = studentRepository.getSolvedProblems(handle);

        // 문제별 rare값 계산 및 update
        for (Integer pid : solvedProblems){
            // rareScoreMap에 이미 계산된 rare값 있는지 확인
            Float rare = rareScoreMap.get(pid);

            // 없으면 계산
            if (rare == null) {
                int solvedStudents = dataCrawlingService.getSolvedStudents(pid);
                rare = (float) (10 * Math.exp(-0.02 * solvedStudents));

                // 계산한 rare값 map에 저장
                rareScoreMap.put(pid, rare);
            }

            // rare값 누적합
            insertRare += rare;
        }

        return insertRare;
    }
}
