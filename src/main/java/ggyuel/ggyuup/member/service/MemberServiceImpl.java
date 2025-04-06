package ggyuel.ggyuup.member.service;

import ggyuel.ggyuup.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public Boolean checkEwhain(String request) {
        String handle = request;
        return memberMapper.checkEwhain(handle);
    }
}
