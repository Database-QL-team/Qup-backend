package ggyuel.ggyuup.member.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequestDTO {

    //  handle : 사용자의 백준 핸들
    private String handle;

}
