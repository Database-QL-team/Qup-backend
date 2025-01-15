package ggyuel.ggyuup.mainPage.controller;

import ggyuel.ggyuup.mainPage.dto.MainPageRespDTO;
import ggyuel.ggyuup.mainPage.service.MainPageService;
import ggyuel.ggyuup.global.apiResponse.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


//
@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final MainPageService mainPageService;

    @GetMapping("")
    public ApiResponse<MainPageRespDTO> getMainPage() {
        // 메인 페이지 정보 가져오기
        MainPageRespDTO mainPageInfo = mainPageService.getMainPage();
        // 성공 응답 반환
        return ApiResponse.onSuccess(mainPageInfo);
    }
}

