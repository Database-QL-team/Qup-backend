package ggyuel.ggyuup.mainPage.repository;

import ggyuel.ggyuup.mainPage.mapper.MainMapper;
import ggyuel.ggyuup.organization.domain.Organizations;
import ggyuel.ggyuup.problem.domain.Problems;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MainRepository {
    private final MainMapper mainMapper;

    public Optional<Organizations> selectEwhaInfo(String groupName) { return mainMapper.selectEwhaInfo(groupName); }
    public Optional<Organizations> selectRivalInfo(String groupName) { return mainMapper.selectRivalInfo(groupName); }
    public List<Problems> selectTodayPs() { return mainMapper.selectTodayPs(); }
}
