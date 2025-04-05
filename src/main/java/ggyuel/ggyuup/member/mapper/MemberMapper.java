package ggyuel.ggyuup.member.mapper;

import ggyuel.ggyuup.member.dto.MemberRankRespDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    boolean checkEwhain(@Param("handle") String handle);
    List<MemberRankRespDTO> selectMemberRank();
    float getMemberRare(@Param("handle") String handle);
    float getMemberBasic(@Param("handle") String handle);
    float getMemberTotal(@Param("handle") String handle);
    void updateMemberScore(@Param("handle") String handle, @Param("basic") float basic, @Param("rare") float rare);

}
