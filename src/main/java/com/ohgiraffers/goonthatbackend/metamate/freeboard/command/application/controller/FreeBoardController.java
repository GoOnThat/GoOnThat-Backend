package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class FreeBoardController {

        @GetMapping("/list")
        public ModelAndView list(ModelAndView mv) {

                mv.setViewName("/board/list");

                return  mv;
        }

        @GetMapping("/write")
        public ModelAndView write(ModelAndView mv) {

                mv.setViewName("/board/write");

                return mv;
        }

        @GetMapping("/detail")
        public ModelAndView detail(ModelAndView mv) {

                mv.setViewName("board/detail");

                return mv;


        }
    }

