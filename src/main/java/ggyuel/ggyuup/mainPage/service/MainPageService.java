package ggyuel.ggyuup.mainPage.service;

import ggyuel.ggyuup.mainPage.dto.GroupInfoRespDTO;
import ggyuel.ggyuup.mainPage.dto.MainPageRespDTO;
import ggyuel.ggyuup.mainPage.dto.TodayPsRespDTO;
import ggyuel.ggyuup.organization.domain.Organizations;

import java.util.ArrayList;
import java.util.Optional;

public interface MainPageService {
    Optional<Organizations> getEwhaInfo();
    Optional<Organizations> getRivalInfo();
    GroupInfoRespDTO getGroupInfo();
    ArrayList<TodayPsRespDTO> getTodayPS();
    MainPageRespDTO getMainPage();
}
