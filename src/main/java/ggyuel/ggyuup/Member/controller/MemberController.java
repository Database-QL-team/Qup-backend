package ggyuel.ggyuup.Member.controller;

import ggyuel.ggyuup.Member.dto.LoginDTO;
import ggyuel.ggyuup.Member.service.MemberService;
import ggyuel.ggyuup.global.apiResponse.code.status.ErrorStatus;
import ggyuel.ggyuup.global.apiResponse.exception.GeneralException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public void login(@RequestBody @Validated LoginDTO request, HttpServletResponse response) {
        Boolean isEwha = memberService.isEwhaStudent(request);
        if(isEwha) {
            // 쿠키 생성 및 설정
            Cookie cookie = new Cookie("handle", request.getHandle());
            cookie.setDomain(".ewhaqup.com");
            cookie.setPath("/");
            cookie.setMaxAge(7*24*60*60);
            cookie.setSecure(false);

            // 쿠키 브라우저에 삽입
            response.addCookie(cookie);
        }
        else {
            throw new GeneralException(ErrorStatus.NOT_EWHAIN);
        }
    }
}
