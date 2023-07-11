package com.ohgiraffers.goonthatbackend.ideaassemble.board.command.application.controller;

import com.ohgiraffers.goonthatbackend.ideaassemble.board.command.application.dto.BoardListDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

