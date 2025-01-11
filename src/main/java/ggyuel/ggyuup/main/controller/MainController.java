package ggyuel.ggyuup.main.controller;

import ggyuel.ggyuup.main.dto.MainResponseDTO;
import ggyuel.ggyuup.main.service.MainPageService;
import ggyuel.ggyuup.main.service.MainPageServiceImpl;
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
    public ApiResponse<MainResponseDTO.MainPageDTO> getMainPage() {
        // 메인 페이지 정보 가져오기
        MainResponseDTO.MainPageDTO MainPageInfo = mainPageService.getMainPage();
        // 성공 응답 반환
        return ApiResponse.onSuccess(MainPageInfo);
    }
}

