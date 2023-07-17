package com.ohgiraffers.goonthatbackend.metamate.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class MainController {
    @GetMapping(value = {"/", "/main"})
    public String main() {

        return "index"; //html 이름
    }


}

