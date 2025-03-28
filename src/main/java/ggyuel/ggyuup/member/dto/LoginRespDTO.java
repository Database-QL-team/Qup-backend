package ggyuel.ggyuup.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRespDTO {
    @JsonProperty
    private String handle;  // 사용자의 백준 핸들

}
