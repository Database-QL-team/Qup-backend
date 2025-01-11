package ggyuel.ggyuup.problem.controller;

import ggyuel.ggyuup.dataCrawling.service.DataCrawlingService;
import ggyuel.ggyuup.problem.service.ProblemAlgo;
import ggyuel.ggyuup.problem.service.ProblemTier;
import ggyuel.ggyuup.problem.dto.ProblemResponseDTO;
import ggyuel.ggyuup.global.apiResponse.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 문제 정렬 관련 요청을 처리하는 컨트롤러 클래스입니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/problems")
public class ProblemController {
    private final DataCrawlingService dataCrawlingService;

    /**
     * 지정된 알고리즘 태그로 필터링된 문제 목록을 반환합니다.
     *
     * @param tag 필터링에 사용할 알고리즘 태그입니다.
     * @return 성공 상태와 함께 필터링된 문제 목록을 포함하는 ApiResponse 객체입니다.
     */
    @GetMapping("/algo")
    public ApiResponse<ArrayList<ProblemResponseDTO.ProblemAlgoDTO>> getProblemAlgo(@RequestParam("tag") String tag) {
        // ProblemAlgo 서비스를 사용하여 지정된 태그로 문제를 가져옵니다
        ArrayList<ProblemResponseDTO.ProblemAlgoDTO> problemAlgoList = ProblemAlgo.getProblemsByTag(tag);
        // 성공 상태와 함께 ApiResponse로 문제 목록을 반환합니다
        return ApiResponse.onSuccess(problemAlgoList);
    }

    /**
     * 지정된 티어로 필터링된 문제 목록을 반환합니다.
     *
     * @param tier 필터링에 사용할 티어입니다.
     * @return 성공 상태와 함께 필터링된 문제 목록을 포함하는 ApiResponse 객체입니다.
     */
    @GetMapping("/tier")
    public ApiResponse<ArrayList<ProblemResponseDTO.ProblemTierDTO>> getProblemTier(@RequestParam("tier") int tier) {
        // ProblemTier 서비스를 사용하여 지정된 티어로 문제를 가져옵니다
        ArrayList<ProblemResponseDTO.ProblemTierDTO> problemTierList = ProblemTier.getProblemsByTier(tier);
        // 성공 상태와 함께 ApiResponse로 문제 목록을 반환합니다
        return ApiResponse.onSuccess(problemTierList);
    }

    @GetMapping("/refresh")
    public void refreshProblems(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("handle")){
                    dataCrawlingService.userRefresh(cookie.getValue());
                }
            }
        }
    }
}