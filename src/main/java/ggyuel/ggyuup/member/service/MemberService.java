package ggyuel.ggyuup.member.service;

import ggyuel.ggyuup.member.dto.LoginDTO;

public interface MemberService {
    Boolean checkEwhain(LoginDTO request);
}
