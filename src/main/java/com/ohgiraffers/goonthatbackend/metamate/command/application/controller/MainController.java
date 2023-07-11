package com.ohgiraffers.goonthatbackend.metamate.command.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class MainController {
    @RequestMapping(value = {"/", "/main"})
    public String main() {

        return "index"; //html 이름
    }
}

