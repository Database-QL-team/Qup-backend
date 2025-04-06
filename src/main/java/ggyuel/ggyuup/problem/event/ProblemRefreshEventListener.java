package ggyuel.ggyuup.problem.event;

import ggyuel.ggyuup.member.service.MemberService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProblemRefreshEventListener {

    private final MemberService memberService;

    public ProblemRefreshEventListener(MemberService memberService) {
        this.memberService = memberService;
    }

    @Async
    @EventListener
    public void handleProblemRefreshedEvent(ProblemRefreshedEvent event) {
        String handle = event.getHandle();
        Set<Integer> newSolvedNums = event.getNewSolvedNums();

        memberService.updateMemberScore(handle, newSolvedNums);
    }
}