package com.ohgiraffers.goonthatbackend.metamate.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserAccountClientResponse {

    @JsonProperty("_embedded") Embedded embedded;
    @JsonProperty("page") Page page;


    public static UserAccountClientResponse empty() {
        return new UserAccountClientResponse(
                new Embedded(List.of()),
                new Page(1, 0, 1, 0));
    }

    public static UserAccountClientResponse of(List<UserAccountManagementDto> userAccounts) {
        return new UserAccountClientResponse(
                new Embedded(userAccounts),
                new Page(userAccounts.size(), userAccounts.size(), 1, 0)
        );
    }

    public List<UserAccountManagementDto> userAccounts() {
        return this.embedded.getUserAccounts();
    }
}


@Getter
@AllArgsConstructor
class Embedded {

    private List<UserAccountManagementDto> userAccounts;
}

@Getter
@AllArgsConstructor
class Page {

    private int size;
    private long totalElements;
    private int totalPages;
    private int number;
}
