package ggyuel.ggyuup.Organizations.domain;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Organizations {
    private int ranking;
    private String groupName;
    private int solvedNum;
}
