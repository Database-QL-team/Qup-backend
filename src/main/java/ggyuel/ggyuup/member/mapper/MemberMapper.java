package ggyuel.ggyuup.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    boolean checkEwhain(@Param("handle") String handle);
}
