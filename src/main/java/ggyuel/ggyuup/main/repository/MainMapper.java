package ggyuel.ggyuup.main.repository;

import ggyuel.ggyuup.organization.domain.Organizations;
import ggyuel.ggyuup.problem.domain.Problems;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MainMapper {
    Optional<Organizations> selectEwhaInfo(@Param("groupName") String groupName);
    Optional<Organizations> selectRivalInfo(@Param("groupName") String groupName);
    List<Problems> selectTodayPs();
}
