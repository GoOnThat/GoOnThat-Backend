package com.ohgiraffers.goonthatbackend.metamate.domain.user;

import com.ohgiraffers.goonthatbackend.metamate.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class MetaUser extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String number;
    private String major;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String provider;

    @Builder
    public MetaUser(String email, String password, String name,
                    String nickname, String number, String major,
                    Role role, String provider) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.number = number;
        this.major = major;
        this.role = role;
        this.provider = provider;
    }

    public void update(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}