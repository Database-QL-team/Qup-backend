package ggyuel.ggyuup.member.controller;

import ggyuel.ggyuup.member.service.MemberService;
import ggyuel.ggyuup.global.apiResponse.code.status.ErrorStatus;
import ggyuel.ggyuup.global.apiResponse.exception.GeneralException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "Member", description = "회원 API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    @Operation(summary = "회원 간단 로그인", description = "회원 간단 로그인 - 핸들 입력, 쿠키 저장")
    public String login(@RequestParam("handle") String handle, HttpServletResponse response) {

        Boolean isEwha = memberService.checkEwhain(handle);
        System.out.println("isEwha : "+ isEwha);

        if(isEwha) {
            // 쿠키 생성 및 설정
            response.addHeader("Set-Cookie",
                    "handle=" + handle + "; Path=/; Max-Age=604800; Secure=true; SameSite=None");

            // redirect -> 문제 업데이트
            return "redirect:/problems/refresh";
        }
        else {
            throw new GeneralException(ErrorStatus.NOT_EWHAIN);
        }
    }
}
