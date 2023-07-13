package com.ohgiraffers.goonthatbackend.metamate.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class MetaUserLoginDto {

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
