package ggyuel.ggyuup.ranking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SelectRankingDTO {
    private String handle;
    private float total;
}
