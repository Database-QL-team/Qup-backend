package ggyuel.ggyuup.problem.event;

import lombok.Getter;

import java.util.Set;

@Getter
public class ProblemRefreshedEvent {
    private final String handle;
    private final Set<Integer> newSolvedNums;

    public ProblemRefreshedEvent(String handle, Set<Integer> newSolvedNums) {
        this.handle = handle;
        this.newSolvedNums = newSolvedNums;
    }
}
