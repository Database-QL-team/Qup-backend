package ggyuel.ggyuup.ranking.controller;

import ggyuel.ggyuup.global.apiResponse.ApiResponse;
import ggyuel.ggyuup.ranking.dto.RankingRespDTO;
import ggyuel.ggyuup.ranking.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {
    @Autowired
    private RankingService rankingService;

    @GetMapping("")
    public ApiResponse<List<RankingRespDTO>> getEwhaRank(){
        List<RankingRespDTO> rankingRespDTOs = rankingService.getEwhaRank();
        return ApiResponse.onSuccess(rankingRespDTOs);
    }
}
