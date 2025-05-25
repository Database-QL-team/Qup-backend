package ggyuel.ggyuup.ranking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RankingRespDTO {
    @JsonProperty
    private String handle;
    @JsonProperty
    private float total;
}
