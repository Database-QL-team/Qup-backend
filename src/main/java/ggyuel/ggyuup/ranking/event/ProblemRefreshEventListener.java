package ggyuel.ggyuup.ranking.event;


import ggyuel.ggyuup.problem.dto.ProblemRefreshRespDTO;
import ggyuel.ggyuup.ranking.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ProblemRefreshEventListener {
    @Autowired
    private RankingService rankingService;

    @Async
    @EventListener
    public void handleProblemRefreshEvent(ProblemRefreshEvent event) {
        ProblemRefreshRespDTO problemRefreshRespDTO = event.getProblemRefreshRespDTO();
        System.out.println("이벤트 리스너 - refreshScores 호출 시작");
        rankingService.refreshScores(problemRefreshRespDTO);
        System.out.println("이벤트 리스너 - refreshScores 호출 끝");
    }
}
