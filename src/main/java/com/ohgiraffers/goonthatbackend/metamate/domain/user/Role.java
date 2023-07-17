package com.ohgiraffers.goonthatbackend.metamate.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST"),
    ASSOCIATE("ROLE_ASSOCIATE"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;
}
