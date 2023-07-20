package com.ohgiraffers.goonthatbackend.metamate.common;


import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @GetMapping(value = {"/", "/index"})
    public String index(
            @LoginUser SessionMetaUser user,
            Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }

        return "index"; //html 이름
    }


}

