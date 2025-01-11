package ggyuel.ggyuup.member.service;

import ggyuel.ggyuup.member.dto.LoginDTO;
import ggyuel.ggyuup.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public Boolean checkEwhain(LoginDTO request) {
        Boolean isExist;
        String handle = request.getHandle();

        int result = memberMapper.checkEwhain(handle);

        if (result == 1) isExist = true;
        else isExist = false;

        return isExist;
    }
}
