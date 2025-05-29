package ggyuel.ggyuup.ranking.event;

import ggyuel.ggyuup.problem.dto.ProblemRefreshRespDTO;
import lombok.Getter;
@Getter
public class ProblemRefreshEvent {
    private final ProblemRefreshRespDTO problemRefreshRespDTO;

    public ProblemRefreshEvent(ProblemRefreshRespDTO problemRefreshRespDTO) {
        this.problemRefreshRespDTO = problemRefreshRespDTO;
    }
}
