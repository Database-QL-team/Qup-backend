package ggyuel.ggyuup.main.service;

import ggyuel.ggyuup.main.dto.MainResponseDTO;
import ggyuel.ggyuup.organization.domain.Organizations;

import java.util.ArrayList;
import java.util.Optional;

public interface MainPageService {
    Optional<Organizations> getEwhaInfo();
    Optional<Organizations> getRivalInfo();
    MainResponseDTO.GroupInfoDTO getGroupInfo();
    ArrayList<MainResponseDTO.TodayPSDTO> getTodayPS();
    MainResponseDTO.MainPageDTO getMainPage();
}
