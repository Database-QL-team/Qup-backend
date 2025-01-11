package ggyuel.ggyuup.member.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    int checkEwhain(@Param("handle") String handle);
}
