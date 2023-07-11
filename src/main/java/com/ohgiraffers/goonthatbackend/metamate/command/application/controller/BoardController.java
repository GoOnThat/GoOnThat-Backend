package com.ohgiraffers.goonthatbackend.metamate.command.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
public class BoardController {


        @GetMapping("")
        public String List() {

                return "board/list";
        }


        @GetMapping("/write")
        public void Write() {

        }

        @GetMapping("/detail")
        public void Detail() {

        }
    }

