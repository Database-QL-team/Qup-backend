package ggyuel.ggyuup.ewhaHistory.service;

import ggyuel.ggyuup.ewhaHistory.dto.EwhaHistoryRespDTO;
import ggyuel.ggyuup.ewhaHistory.mapper.EwhaHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EwhaHistoryServiceImpl implements EwhaHistoryService {
    private final EwhaHistoryMapper ewhaHistoryMapper;
    @Override
    public List<EwhaHistoryRespDTO> getEwhaHistory() {
        List<EwhaHistoryRespDTO> ewhaHistoryRespDTOs = ewhaHistoryMapper.selectEwhaHistory();
        return ewhaHistoryRespDTOs;
    }
}
