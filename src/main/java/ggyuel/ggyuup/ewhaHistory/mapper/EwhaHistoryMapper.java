package ggyuel.ggyuup.ewhaHistory.mapper;

import ggyuel.ggyuup.ewhaHistory.dto.EwhaHistoryRespDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EwhaHistoryMapper {
    List<EwhaHistoryRespDTO> selectEwhaHistory();
}
