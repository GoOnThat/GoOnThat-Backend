package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.controller;

import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.FreeBoardService;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoard;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @Autowired
    public FreeBoardController(FreeBoardService freeBoardService) {
        this.freeBoardService = freeBoardService;
    }

    /* 게시판 전체 목록 조회 */
    @GetMapping("/list")
    public String list(Model model, FreeBoardListDTO freeBoardListDTO) {
        List<FreeBoard> boardList = freeBoardService.getAllBoards(freeBoardListDTO);
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    /* 글쓰기 페이지 조회 */
    @GetMapping("/write")
    public ModelAndView write(ModelAndView mv) {

        mv.setViewName("/board/write");

        return mv;
    }

    /* 글쓰기 페이지 작성 */
    @PostMapping("/write")
    public String enrollContent(@LoginUser SessionMetaUser user,
                                FreeBoardWriteDTO freeBoardWrite) {

        LocalDate currentTime = LocalDate.now();
        freeBoardWrite.setBoardCreateDate(currentTime);
        freeBoardWrite.setBoardModifiedDate(currentTime);
        freeBoardWrite.setBoardDeleteYn("N");

        freeBoardService.write(freeBoardWrite);

        return "redirect:/board/list";
    }

    /* 게시판 글 번호 별 세부 조회 */
    @GetMapping("/detail/{boardNo}")
    public String detail(@PathVariable Long boardNo, Model model) {
        FreeBoardDetailDTO freeBoardDetailDTO = freeBoardService.detailBoard(boardNo);
        System.out.println("Controller = " + freeBoardDetailDTO);
        model.addAttribute("detailBoard", freeBoardDetailDTO);
        System.out.println(freeBoardDetailDTO);
        return "board/detail";
    }

    /* 게시판 글 번호 별 세부 수정 ? ? */
    @PostMapping("/detail")
    public String detail(Model model, FreeBoardDetailDTO freeBoardDetailDTO) {

        String boardTitle = freeBoardDetailDTO.getBoardTitle();
        String boardContent = freeBoardDetailDTO.getBoardContent();
        String boardCategory = freeBoardDetailDTO.getBoardCategory();

        model.addAttribute("boardTitle", boardTitle);
        model.addAttribute("boardContent", boardContent);
        model.addAttribute("boardCategory", boardCategory);

        freeBoardService.enrolledwrite(freeBoardDetailDTO);

        return "board/detail";
    }

}

