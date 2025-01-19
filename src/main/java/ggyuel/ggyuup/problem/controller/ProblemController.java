package ggyuel.ggyuup.problem.controller;

import ggyuel.ggyuup.dataCrawling.service.DataCrawlingService;
import ggyuel.ggyuup.problem.dto.ProblemAlgoRespDTO;
import ggyuel.ggyuup.problem.dto.ProblemTierRespDTO;
import ggyuel.ggyuup.problem.service.ProblemService;
import ggyuel.ggyuup.global.apiResponse.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/problems")
@Tag(name = "Problem", description = "Problem API")
public class ProblemController {

    private final ProblemService problemService;
    private final DataCrawlingService dataCrawlingService;


    @GetMapping("/algo")
    @Operation(summary = "알고리즘별 문제 검색", description = "알고리즘별로 문제 정렬")
    public ApiResponse<List<ProblemAlgoRespDTO>> getProblemAlgo(@RequestParam("tag") String algo) {

        List<ProblemAlgoRespDTO> problemAlgoRespDTOList = problemService.getProblemsByAlgo(algo);

        return ApiResponse.onSuccess(problemAlgoRespDTOList);
    }


    @GetMapping("/tier")
    @Operation(summary = "티어별 문제 검색", description = "티어별로 문제 정렬")
    public ApiResponse<List<ProblemTierRespDTO>> getProblemTier(@RequestParam("tier") int tier) {
        List<ProblemTierRespDTO> problemTierRespDTOList = problemService.getProblemsByTier(tier);
        return ApiResponse.onSuccess(problemTierRespDTOList);
    }

    @GetMapping("/refresh")
    @Operation(summary = "문제 리프레시", description = "리프레시 버튼 눌렀을 때 문제 리프레시")
    public void refreshProblems(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("handle")){
                    dataCrawlingService.userRefresh(cookie.getValue());
                }
            }
        }
    }
}