package ggyuel.ggyuup.ewhaHistory.controller;

import ggyuel.ggyuup.ewhaHistory.dto.EwhaHistoryRespDTO;
import ggyuel.ggyuup.ewhaHistory.service.EwhaHistoryService;
import ggyuel.ggyuup.global.apiResponse.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ewhahistory")
@Tag(name = "Ewha History", description = "이대 백준 데이터 조회")
public class EwhaHistoryController {
    @Autowired
    EwhaHistoryService ewhaHistoryService;

    @GetMapping("")
    @Operation(summary = "일별 이대 백준 데이터", description = "일별 백준 순위 및 푼 문제 수 조회")
    public ApiResponse<List<EwhaHistoryRespDTO>> getEwhaHistory() {
        // 일별 이화 백준 데이터
        List<EwhaHistoryRespDTO> ewhaHistoryRespDTOList = ewhaHistoryService.getEwhaHistory();
        // 성공 응답 반환
        return ApiResponse.onSuccess(ewhaHistoryRespDTOList);
    }
}
