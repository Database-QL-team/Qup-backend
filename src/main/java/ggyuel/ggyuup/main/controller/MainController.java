package ggyuel.ggyuup.main.controller;

import ggyuel.ggyuup.main.dto.MainResponseDTO;
import ggyuel.ggyuup.main.service.MainPage;
import ggyuel.ggyuup.global.apiResponse.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


//
@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    /**
     * 메인 페이지 정보를 가져오는 엔드포인트
     *
     * @return ApiResponse<MainResponseDTO.MainPageDTO> 메인 페이지 정보가 포함된 성공 응답
     */
    @GetMapping("")
    public ApiResponse<MainResponseDTO.MainPageDTO> getMainPage() {
        // 메인 페이지 정보 가져오기
        MainResponseDTO.MainPageDTO MainPageInfo = MainPage.getMainPage();
        // 성공 응답 반환
        return ApiResponse.onSuccess(MainPageInfo);
    }
}

