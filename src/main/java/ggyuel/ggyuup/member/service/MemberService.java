package ggyuel.ggyuup.member.service;

import ggyuel.ggyuup.member.dto.LoginRequestDTO;

public interface MemberService {
    Boolean checkEwhain(LoginRequestDTO request);
}
