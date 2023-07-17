package com.ohgiraffers.goonthatbackend.metamate.common;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
    @GetMapping(value = {"/", "/main"})
    public String main() {

        return "index"; //html 이름
    }
}

