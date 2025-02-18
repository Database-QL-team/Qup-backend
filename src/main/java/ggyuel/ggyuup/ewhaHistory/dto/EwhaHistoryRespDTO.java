package ggyuel.ggyuup.ewhaHistory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class EwhaHistoryRespDTO {
    @JsonProperty
    private LocalDate date;
    @JsonProperty
    private int rating;
    @JsonProperty
    private int solvedNum;
}
