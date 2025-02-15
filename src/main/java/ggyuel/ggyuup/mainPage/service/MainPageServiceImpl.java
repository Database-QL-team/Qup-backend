package ggyuel.ggyuup.mainPage.service;

import ggyuel.ggyuup.mainPage.dto.GroupInfoRespDTO;
import ggyuel.ggyuup.mainPage.dto.MainPageRespDTO;
import ggyuel.ggyuup.mainPage.dto.TodayPsRespDTO;
import ggyuel.ggyuup.organization.domain.Organizations;
import ggyuel.ggyuup.problem.domain.Problems;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {

    private final MainRepository mainRepository;
    private final String groupName = "이화여자대학교";

    @Override
    public Optional<Organizations> getEwhaInfo() {
        Optional<Organizations> ewha = mainRepository.selectEwhaInfo(groupName);
        return ewha;
    }

    @Override
    public Optional<Organizations> getRivalInfo() {
        Optional<Organizations> rival = mainRepository.selectRivalInfo(groupName);
        return rival;
    }

    @Override
    public GroupInfoRespDTO getGroupInfo() {
        Optional<Organizations> ewhaInfo = getEwhaInfo();
        Optional<Organizations> rivalInfo = getRivalInfo();

        int ewhaRank = ewhaInfo.get().getRanking();
        String rivalName = rivalInfo.get().getGroupName();
        int rivalRank = rivalInfo.get().getRanking();
        int solvedNumGap = rivalInfo.get().getSolvedNum() - ewhaInfo.get().getSolvedNum();

        GroupInfoRespDTO groupInfoDTO = GroupInfoRespDTO.builder()
                .ewhaRank(ewhaRank)
                .rivalRank(rivalRank)
                .rivalName(rivalName)
                .solvedNumGap(solvedNumGap)
                .build();

        return groupInfoDTO;
    }

    @Override
    public ArrayList<TodayPsRespDTO> getTodayPS() {
        ArrayList<TodayPsRespDTO> todayPSDTOlist = new ArrayList<>();
        List<Problems> todayPsList = mainRepository.selectTodayPs();

        for (Problems todayPs: todayPsList) {
            int problemId = todayPs.getProblemId();
            String title = todayPs.getTitle();
            String link = todayPs.getLink();
            int tier = todayPs.getTier();
            int solvedNum = todayPs.getSolvedNum();
            TodayPsRespDTO todayPSDTO = TodayPsRespDTO.builder()
                    .problemId(problemId)
                    .title(title)
                    .link(link)
                    .tier(tier)
                    .solvedNum(solvedNum)
                    .build();

            todayPSDTOlist.add(todayPSDTO);
        }

        return todayPSDTOlist;
    }

    @Override
    public MainPageRespDTO getMainPage() {
        GroupInfoRespDTO groupInfoDTO = getGroupInfo();
        ArrayList<TodayPsRespDTO> todayPSDTOList = getTodayPS();

        MainPageRespDTO mainPageDTO = MainPageRespDTO.builder()
                .groupInfo(groupInfoDTO)
                .todayPSList(todayPSDTOList)
                .build();

        return mainPageDTO;
    }
}

