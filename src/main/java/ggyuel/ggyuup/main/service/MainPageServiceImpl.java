package ggyuel.ggyuup.main.service;

import ggyuel.ggyuup.main.dto.MainResponseDTO;
import ggyuel.ggyuup.main.repository.MainMapper;
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

    private final MainMapper mainMapper;
    private final String groupName = "이화여자대학교";

    @Override
    public Optional<Organizations> getEwhaInfo() {
        Optional<Organizations> ewha = mainMapper.selectEwhaInfo(groupName);
        return ewha;
    }

    @Override
    public Optional<Organizations> getRivalInfo() {
        Optional<Organizations> rival = mainMapper.selectRivalInfo(groupName);
        return rival;
    }

    @Override
    public MainResponseDTO.GroupInfoDTO getGroupInfo() {
        Optional<Organizations> ewhaInfo = getEwhaInfo();
        Optional<Organizations> rivalInfo = getRivalInfo();

        int ewhaRank = ewhaInfo.get().getRanking();
        String rivalName = rivalInfo.get().getGroupName();
        int rivalRank = rivalInfo.get().getRanking();
        int solvedNumGap = rivalInfo.get().getSolvedNum() - ewhaInfo.get().getSolvedNum();

        MainResponseDTO.GroupInfoDTO groupInfoDTO = new MainResponseDTO.GroupInfoDTO(ewhaRank, rivalRank, rivalName, solvedNumGap);

        return groupInfoDTO;
    }

    @Override
    public ArrayList<MainResponseDTO.TodayPSDTO> getTodayPS() {
        ArrayList<MainResponseDTO.TodayPSDTO> todayPSDTOlist = new ArrayList<>();
        List<Problems> todayPsList = mainMapper.selectTodayPs();

        for (Problems todayPs: todayPsList) {
            int problemId = todayPs.getProblemId();
            String title = todayPs.getTitle();
            String link = todayPs.getLink();
            int tier = todayPs.getTier();
            int solvedNum = todayPs.getSolvedNum();
            MainResponseDTO.TodayPSDTO todayPSDTO = new MainResponseDTO.TodayPSDTO(problemId, title, link, tier, solvedNum);

            todayPSDTOlist.add(todayPSDTO);
        }

        return todayPSDTOlist;
    }

    @Override
    public MainResponseDTO.MainPageDTO getMainPage() {
        MainResponseDTO.GroupInfoDTO groupInfoDTO = getGroupInfo();
        ArrayList<MainResponseDTO.TodayPSDTO> todayPSDTOList = getTodayPS();

        MainResponseDTO.MainPageDTO mainPageDTO = new MainResponseDTO.MainPageDTO(groupInfoDTO, todayPSDTOList);

        return mainPageDTO;
    }
}

