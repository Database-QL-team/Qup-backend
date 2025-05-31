package ggyuel.ggyuup.mainPage.controller;

import ggyuel.ggyuup.mainPage.dto.MainPageRespDTO;
import ggyuel.ggyuup.mainPage.service.MainPageService;
import ggyuel.ggyuup.global.apiResponse.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//
@RestController
@RequestMapping("/main")
@Tag(name = "Main Page", description = "메인페이지 API")
public class MainController {

    @Autowired
    private MainPageService mainPageService;

    @GetMapping("")
    @Operation(summary = "메인페이지", description = "메인페이지 - 그룹 정보, 오늘의 문제")
    public ApiResponse<MainPageRespDTO> getMainPage() {
        // 메인 페이지 정보 가져오기
        MainPageRespDTO mainPageInfo = mainPageService.getMainPage();
        // 성공 응답 반환
        return ApiResponse.onSuccess(mainPageInfo);
    }
}

