package ggyuel.ggyuup.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberRankRespDTO {
    @JsonProperty
    private int rank;
    @JsonProperty
    private String handle;
    @JsonProperty
    private int score;
}
