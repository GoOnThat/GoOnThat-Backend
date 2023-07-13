package com.ohgiraffers.goonthatbackend.metamate.member.controller;

import com.ohgiraffers.goonthatbackend.metamate.member.dto.MetaUserJoinDto;
import com.ohgiraffers.goonthatbackend.metamate.member.service.MetaUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MetaUserJoinController {

    private final MetaUserService metaUserService;

    @GetMapping("/join")
    public String signup(@ModelAttribute("metaUserJoinDto") MetaUserJoinDto metaUserJoinDto) {
        return "signup";
    }

    @PostMapping("/join")
    public String signup(@Valid @ModelAttribute("metaUserJoinDto") MetaUserJoinDto metaUserJoinDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        metaUserService.createUser(metaUserJoinDto);

        return "redirect:/";
    }
}
