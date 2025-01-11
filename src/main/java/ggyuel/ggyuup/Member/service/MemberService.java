package ggyuel.ggyuup.Member.service;

import ggyuel.ggyuup.Member.dto.LoginDTO;

public interface MemberService {
    Boolean checkEwhain(LoginDTO request);
}
