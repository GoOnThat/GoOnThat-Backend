package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.controller;

import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.FreeBoardPostService;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardPostService freeBoardService;

    /* 게시판 전체 목록 조회 */
    @GetMapping("/list")
    public String list(@LoginUser SessionMetaUser user,
                       Model model) {

        if(user !=null) {
            model.addAttribute("user", user);
        }
        List<FreeBoardListDTO> boardList = freeBoardService.getAllPosts();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    /* 글쓰기 페이지 조회 */
    @GetMapping("/write")
    public String write(@ModelAttribute("freeBoardPost")
                        FreeBoardWriteDTO freeBoardWriteDTO,
                        @LoginUser SessionMetaUser user,
                        Model model) {
        if(user !=null) {
            model.addAttribute("user", user);
        }
        return "board/write";
    }

    /* 글쓰기 페이지 작성 */
    @PostMapping("/write")
    public String writeSave(@ModelAttribute("freeBoardPost")
                            FreeBoardWriteDTO freeBoardWriteDTO,
                            @LoginUser SessionMetaUser user,
                            Model model) {
        if(user !=null) {
            model.addAttribute("user", user);
        }
        freeBoardService.savePost(freeBoardWriteDTO,user);

        return "redirect:/board/list";
    }

    /* 게시판 글 번호 별 세부 조회 */
//    @GetMapping("/detail/{boardNo}")
//    public String detail(@PathVariable Long boardNo, Model model) {
//        FreeBoardDetailDTO freeBoardDetailDTO = freeBoardService.detailBoard(boardNo);
//        System.out.println("Controller = " + freeBoardDetailDTO);
//        model.addAttribute("detailBoard", freeBoardDetailDTO);
//        System.out.println(freeBoardDetailDTO);
//        return "board/detail";
//    }
//
//    @PostMapping("/detail")
//    public String detail(Model model, FreeBoardDetailDTO freeBoardDetailDTO) {
//
//        String boardTitle = freeBoardDetailDTO.getBoardTitle();
//        String boardContent = freeBoardDetailDTO.getBoardContent();
//        String boardCategory = freeBoardDetailDTO.getBoardCategory();
//
//        model.addAttribute("boardTitle", boardTitle);
//        model.addAttribute("boardContent", boardContent);
//        model.addAttribute("boardCategory", boardCategory);
//
//        freeBoardService.enrolledwrite(freeBoardDetailDTO);
//
//        return "board/detail";
//    }

}

