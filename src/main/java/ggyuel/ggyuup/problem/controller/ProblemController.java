package ggyuel.ggyuup.problem.controller;

import ggyuel.ggyuup.dataCrawling.service.DataCrawlingService;
import ggyuel.ggyuup.problem.dto.ProblemAlgoRespDTO;
import ggyuel.ggyuup.problem.dto.ProblemTierRespDTO;
import ggyuel.ggyuup.problem.service.ProblemService;
import ggyuel.ggyuup.global.apiResponse.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/problems")
public class ProblemController {

    private final ProblemService problemService;
    private final DataCrawlingService dataCrawlingService;


    @GetMapping("/algo")
    public ApiResponse<List<ProblemAlgoRespDTO>> getProblemAlgo(@RequestParam("tag") String algo) {

        List<ProblemAlgoRespDTO> problemAlgoRespDTOList = problemService.getProblemsByAlgo(algo);

        return ApiResponse.onSuccess(problemAlgoRespDTOList);
    }


    @GetMapping("/tier")
    public ApiResponse<List<ProblemTierRespDTO>> getProblemTier(@RequestParam("tier") int tier) {
        List<ProblemTierRespDTO> problemTierRespDTOList = problemService.getProblemsByTier(tier);
        return ApiResponse.onSuccess(problemTierRespDTOList);
    }

    @GetMapping("/refresh")
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