package ggyuel.ggyuup.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    boolean checkEwhain(@Param("handle") String handle);
    List<String> selectEwhain();
}
