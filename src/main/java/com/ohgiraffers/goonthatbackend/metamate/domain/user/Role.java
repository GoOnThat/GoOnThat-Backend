package com.ohgiraffers.goonthatbackend.metamate.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ASSOCIATE("ROLE_ASSOCIATE", "준회원"),
    USER("ROLE_USER", "정회원"),
    ADMIN("ROLE_ADMIN", "관리자");

    private String value;
    private String description;
}
