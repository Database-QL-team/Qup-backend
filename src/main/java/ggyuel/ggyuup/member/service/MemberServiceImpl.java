package ggyuel.ggyuup.member.service;

import ggyuel.ggyuup.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Boolean checkEwhain(String request) {
        String handle = request;
        return memberMapper.checkEwhain(handle);
    }
}
