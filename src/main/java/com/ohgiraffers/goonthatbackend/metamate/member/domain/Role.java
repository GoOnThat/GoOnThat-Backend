package com.ohgiraffers.goonthatbackend.metamate.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ASSOCIATE("ROLE_ASSOCIATE"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;
}
