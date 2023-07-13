package com.ohgiraffers.goonthatbackend.metamate.member.dto;

import com.ohgiraffers.goonthatbackend.metamate.member.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MetaUserDto {
    /**
     * MetaUserJoinDto에서 입력받은 정보를 MetaUserDto로 변환하고
     * MetaUser(Entity)로 생성하기 위해 사용되는 Dto
     */

    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String number;
    private String major;
    private Role role;

    @Builder

    public MetaUserDto(Long id, String email, String password, String name, String nickname, String number, String major, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.number = number;
        this.major = major;
        this.role = role;
    }
}
