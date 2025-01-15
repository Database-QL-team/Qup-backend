package ggyuel.ggyuup.member.controller;

import ggyuel.ggyuup.member.service.MemberService;
import ggyuel.ggyuup.global.apiResponse.code.status.ErrorStatus;
import ggyuel.ggyuup.global.apiResponse.exception.GeneralException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public String login(@RequestParam("handle") String handle, HttpServletResponse response) {

        Boolean isEwha = memberService.checkEwhain(handle);
        //Boolean isEwha = memberService.checkEwhain(request.getHandle());
        System.out.println("isEwha : "+ isEwha);

        if(isEwha) {
            // 쿠키 생성 및 설정
            Cookie cookie = new Cookie("handle", handle);
            System.out.println(cookie);
            //cookie.setDomain("localhost");
            cookie.setDomain(".ewhaqup.com");
            cookie.setPath("/");
            cookie.setMaxAge(7*24*60*60);
            cookie.setSecure(false);

            // 쿠키 브라우저에 삽입
            response.addCookie(cookie);

            // redirect -> 문제 업데이트
            return "redirect:/problems/refresh";
        }
        else {
            throw new GeneralException(ErrorStatus.NOT_EWHAIN);
        }
    }
}
