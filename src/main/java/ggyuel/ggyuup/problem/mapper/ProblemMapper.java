package ggyuel.ggyuup.problem.mapper;

import ggyuel.ggyuup.problem.dto.ProblemAlgoRespDTO;
import ggyuel.ggyuup.problem.dto.ProblemTierRespDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface ProblemMapper {
    List<ProblemAlgoRespDTO> selectProblemsByAlgo(@Param("algoId") String algoId, @Param("pageable") Pageable pageable);
    List<ProblemTierRespDTO> selectProblemsByTier(@Param("tier") int tier, @Param("pageable") Pageable pageable);
    Long selectTotalProblemCountByAlgo(@Param("algo") String algo);
    Long selectTotalProblemCountByTier(@Param("tier") int tier);
}
