package ggyuel.ggyuup.member.repository;

import ggyuel.ggyuup.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final MemberMapper memberMapper;

    public Boolean checkEwhain(String handle) {
        boolean isEwhain = memberMapper.checkEwhain(handle);
        return isEwhain;
    }
}
