package ggyuel.ggyuup.ewhaHistory.service;

import ggyuel.ggyuup.ewhaHistory.dto.EwhaHistoryRespDTO;
import ggyuel.ggyuup.ewhaHistory.mapper.EwhaHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EwhaHistoryServiceImpl implements EwhaHistoryService {
    @Autowired
    private EwhaHistoryMapper ewhaHistoryMapper;
    @Override
    public List<EwhaHistoryRespDTO> getEwhaHistory() {
        List<EwhaHistoryRespDTO> ewhaHistoryRespDTOs = ewhaHistoryMapper.selectEwhaHistory();
        return ewhaHistoryRespDTOs;
    }
}
