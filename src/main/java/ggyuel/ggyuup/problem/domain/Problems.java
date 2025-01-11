package ggyuel.ggyuup.problem.domain;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Problems {
    private int problemId;
    private String title;
    private String link;
    private int tier;
    private int solvedNum;
}
