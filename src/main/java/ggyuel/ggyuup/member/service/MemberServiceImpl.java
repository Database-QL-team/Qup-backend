package ggyuel.ggyuup.member.service;

import ggyuel.ggyuup.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Boolean checkEwhain(String request) {
        String handle = request;
        return memberRepository.checkEwhain(handle);
    }
}
