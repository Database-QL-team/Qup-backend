package ggyuel.ggyuup.ranking.service;

import ggyuel.ggyuup.dataCrawling.service.DataCrawlingService;
import ggyuel.ggyuup.dynamoDB.repository.StudentRepository;
import ggyuel.ggyuup.member.mapper.MemberMapper;
import ggyuel.ggyuup.problem.dto.ProblemRefreshRespDTO;
import ggyuel.ggyuup.ranking.dto.RankingRespDTO;
import ggyuel.ggyuup.ranking.dto.SelectRankingDTO;
import ggyuel.ggyuup.ranking.dto.UserLevelStatRespDTO;
import ggyuel.ggyuup.ranking.mapper.RankingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class RankingServiceImpl implements RankingService {

    @Autowired
    private RankingMapper rankingMapper;
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
        List<SelectRankingDTO> selectRankingDTOList = rankingMapper.selectEwhaRank();
        List<RankingRespDTO> rankingRespDTOList = new ArrayList<>();

        int rank = 1;
        int sameRankCount = 1;
        float previousTotal = -1;

        for (int i = 0; i < selectRankingDTOList.size(); i++) {
            SelectRankingDTO dto = selectRankingDTOList.get(i);
            RankingRespDTO rankingRespDTO;

            if (i == 0) {
                // 첫 번째 순위
                rankingRespDTO = RankingRespDTO.builder()
                        .handle(dto.getHandle())
                        .total(dto.getTotal())
                        .rank(rank)
                        .build();
            }
            else {
                if (dto.getTotal() == previousTotal) {
                    rankingRespDTO = RankingRespDTO.builder()
                            .handle(dto.getHandle())
                            .total(dto.getTotal())
                            .rank(rank)
                            .build();
                    sameRankCount++;
                }
                else {
                    rank += sameRankCount;
                    sameRankCount = 1;
                    rankingRespDTO = RankingRespDTO.builder()
                            .handle(dto.getHandle())
                            .total(dto.getTotal())
                            .rank(rank)
                            .build();
                }
            }

            rankingRespDTOList.add(rankingRespDTO);

            previousTotal = dto.getTotal();
        }

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
        float updatedTotal = Math.round((updatedBasic + updatedRare) * 100) / 100.0f;

        // 점수들 db에 업데이트
        rankingMapper.refreshScores(handle, updatedTotal, updatedBasic, updatedRare);
    }


    // refresh 버튼 - basic 업데이트
    @Override
    public float refreshBasic(String handle, List<Integer> updatedProblems) {

        float addBasic = 0;

        for (int pid : updatedProblems) {
            addBasic += selectTier(pid);
        }

        // 기존 basic 점수에 더해서 반환
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
    @Scheduled(cron = "00 45 10 * * ?")
    public void updateRankingTable() throws InterruptedException {

        // 기존 table의 data delete
        rankingMapper.deleteScores();

        // 이화여대 학생 목록 불러오기
        List<String> handleList = memberMapper.selectEwhain();

        // rare 점수 중복 계산 방지 위한 문제별 rare값 저장소
        Map<Integer, Float> rareScoreMap = new HashMap<>();

        // basic 점수 중복 계산 방지 위한 문제별 basic값 저장소
        Map<Integer, Integer> basicScoreMap = new HashMap<>();

        for (String handle : handleList) {
            System.out.println("handle: " + handle);

            // updateRare 호출해서 insert할 rare 점수 get
            float insertRare = updateRare(handle, rareScoreMap);

            // updateBasic 호출해서 insert할 basic 점수 get
            float insertBasic = updateBasic(handle, basicScoreMap);

            // insert할 total 점수 계산
            float insertTotal = Math.round((insertBasic + insertRare) * 100) / 100.0f;

            // basic, rare, total 점수 insert
            rankingMapper.insertScores(handle, insertTotal, insertBasic, insertRare);
        }
    }


    // ranking table 정기 갱신 - basic 업데이트
    @Override
    public float updateBasic(String handle, Map<Integer, Integer> basicScoreMap) throws InterruptedException {
        System.out.println("updateBasic 호출");
        float insertBasic = 0;

        String url = "https://solved.ac/api/v3/user/problem_stats?handle=" + handle;

        try {
            ResponseEntity<List<UserLevelStatRespDTO>> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<UserLevelStatRespDTO>>() {}
                    );

            List<UserLevelStatRespDTO> userLevelStats = response.getBody();

            if (userLevelStats != null) {
                for (UserLevelStatRespDTO userLevelStat : userLevelStats) {
                    insertBasic += (userLevelStat.getLevel()) * (userLevelStat.getSolved());
                }
            }
        } catch (HttpClientErrorException e) {
            System.out.println("404 에러 발생");
            Set<Integer> problemNums = studentRepository.getSolvedProblems(handle);
            Set<Integer> basicProblems = basicScoreMap.keySet();
            for(int pid : problemNums) {
                if (basicProblems.contains(pid)){
                    insertBasic += basicScoreMap.get(pid);
                }
                else {
                    Thread.sleep(100);
                    int basicScore = selectTier(pid);
                    insertBasic += basicScore;
                    basicScoreMap.put(pid, basicScore);
                }
            }
        }

        System.out.println("insertBasic: " + insertBasic);
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

    @Override
    public int selectTier(Integer pid) {

        int tier = 0;

        String url = UriComponentsBuilder
                .fromHttpUrl("https://solved.ac/api/v3/problem/show")
                .queryParam("problemId", pid)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-solvedac-language", "ko");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Map.class
            );

            Map<String, Object> response = responseEntity.getBody();
            Object level = response.get("level");

            if (level != null) {
                tier = (Integer) level;
            }

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("404 Not Found (pid: " + pid + "): " + e.getMessage());
            } else {
                System.out.println("HTTP Error (pid: " + pid + "): " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error (pid: " + pid + "): " + e.getMessage());
        }

        return tier;
    }
}
