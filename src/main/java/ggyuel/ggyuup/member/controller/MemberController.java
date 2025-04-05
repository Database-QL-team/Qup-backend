package ggyuel.ggyuup.member.controller;

import ggyuel.ggyuup.global.apiResponse.ApiResponse;
import ggyuel.ggyuup.member.dto.LoginRespDTO;
import ggyuel.ggyuup.member.dto.MemberRankRespDTO;
import ggyuel.ggyuup.member.service.MemberService;
import ggyuel.ggyuup.global.apiResponse.code.status.ErrorStatus;
import ggyuel.ggyuup.global.apiResponse.exception.GeneralException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.apigatewayv2.model.Api;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "Member", description = "회원 API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    @Operation(summary = "회원 간단 로그인", description = "회원 간단 로그인 - 핸들 입력, 쿠키 저장")
    public ApiResponse<LoginRespDTO> login(@RequestParam("handle") String handle) {

        Boolean isEwha = memberService.checkEwhain(handle);
        System.out.println("isEwha : "+ isEwha);

        if(isEwha) {
            LoginRespDTO loginRespDTO = LoginRespDTO.builder()
                    .handle(handle)
                    .build();
            return ApiResponse.onSuccess(loginRespDTO);
        }
        else {
            throw new GeneralException(ErrorStatus.NOT_EWHAIN);
        }
    }

    @GetMapping("/rank")
    public ApiResponse<List<MemberRankRespDTO>> getEwhaRank() {
        List<MemberRankRespDTO> memberRankRespDTOs = memberService.selectMemberRank();
        return ApiResponse.onSuccess(memberRankRespDTOs);
    }
}
