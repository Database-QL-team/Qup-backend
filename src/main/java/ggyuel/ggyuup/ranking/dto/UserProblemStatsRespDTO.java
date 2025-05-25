package ggyuel.ggyuup.ranking.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserProblemStatsRespDTO {
    private String handle;
    private List<UserLevelStatRespDTO> items;
}
