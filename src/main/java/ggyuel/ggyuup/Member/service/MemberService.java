package ggyuel.ggyuup.Member.service;

import ggyuel.ggyuup.Member.dto.LoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberService {
    Boolean checkEwhain(LoginDTO request);
}
