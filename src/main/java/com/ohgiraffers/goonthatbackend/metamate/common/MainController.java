package com.ohgiraffers.goonthatbackend.metamate.common;

import org.springframework.web.bind.annotation.RequestMapping;

public class MainController {
    @RequestMapping(value = {"/", "/main"})
    public String main() {

        return "index"; //html 이름
    }
}

