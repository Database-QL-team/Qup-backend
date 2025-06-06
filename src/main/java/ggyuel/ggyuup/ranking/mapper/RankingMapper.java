package ggyuel.ggyuup.ranking.mapper;

import ggyuel.ggyuup.ranking.dto.SelectRankingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RankingMapper {
    List<SelectRankingDTO> selectEwhaRank();

    void insertScores(@Param("handle") String handle, @Param("total") float total, @Param("basic") float basic, @Param("rare") float rare);
    void deleteScores();

    float selectBasic(@Param("handle") String handle);
    float selectRare(@Param("handle") String handle);
    void refreshScores(@Param("handle") String handle, @Param("total") float total, @Param("basic") float basic, @Param("rare") float rare);
}
