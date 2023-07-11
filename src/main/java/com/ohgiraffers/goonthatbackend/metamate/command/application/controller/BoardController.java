package com.ohgiraffers.goonthatbackend.metamate.command.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

        @GetMapping("/list")
        public String list() {

                return  "board/list";
        }

        @GetMapping("/write")
        public String write() {

                return "board/write";
        }

        @GetMapping("/detail")
        public String detail() {
                return "board/detail";
        }
    }

