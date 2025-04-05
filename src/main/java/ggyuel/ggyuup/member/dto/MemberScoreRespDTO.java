package ggyuel.ggyuup.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberScoreRespDTO {
    @JsonProperty
    private String handle;
    @JsonProperty
    private float rare;
    @JsonProperty
    private float basic;
    @JsonProperty
    private float total;
}
