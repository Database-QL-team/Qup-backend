package ggyuel.ggyuup.Member.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginDTO {

    //  handle : 사용자의 백준 핸들
    private String handle;

}
