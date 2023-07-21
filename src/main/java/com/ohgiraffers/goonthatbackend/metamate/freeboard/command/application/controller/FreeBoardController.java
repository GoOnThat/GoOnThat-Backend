package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.controller;

import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.FreeBoardPostService;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public String list(@LoginUser SessionMetaUser user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        List<FreeBoardListDTO> boardList = freeBoardService.getAllPosts();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    /* 글쓰기 페이지 조회 */
    @GetMapping("/write")
    public String write(@ModelAttribute("freeBoardWriteDTO") FreeBoardWriteDTO freeBoardWriteDTO,
                        @LoginUser SessionMetaUser user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "board/write";
    }

    /* 글쓰기 페이지 작성 */
    @PostMapping("/write")
    public String writeSave(@ModelAttribute("freeBoardWriteDTO") FreeBoardWriteDTO freeBoardWriteDTO,
                            @LoginUser SessionMetaUser user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        freeBoardService.savePost(freeBoardWriteDTO, user);

        return "redirect:/board/list";
    }

    /* 게시판 글 번호 별 세부 조회 */
    @GetMapping("/detail/{boardNo}")
    public String detail(@PathVariable Long boardNo, @ModelAttribute("freeBoardDetailDTO") FreeBoardDetailDTO freeBoardDetailDTO,
                         @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("boardNo", boardNo);

        model.addAttribute("boardDetail", freeBoardService.getDetailPosts(boardNo));
        return "board/detail";
    }

    /* 게시글 수정 페이지 조회 */
    @GetMapping("/edit/{boardNo}")
    public String edit(@PathVariable Long boardNo, @ModelAttribute("freeBoardWriteDTO") FreeBoardWriteDTO freeBoardWriteDTO,
                       @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        FreeBoardDetailDTO boardDetail = freeBoardService.getDetailPosts(boardNo);

        model.addAttribute("boardDetail", boardDetail);
        return "board/edit";
    }

    /* 게시글 수정 */
    @PostMapping("/edit/{boardNo}")
    public String editSave(@PathVariable Long boardNo, @ModelAttribute("freeBoardEditDTO") FreeBoardWriteDTO freeBoardWriteDTO,
                           @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        freeBoardService.updatePost(boardNo, freeBoardWriteDTO, user);

        return "redirect:/board/detail/" + boardNo;
    }

    /* 게시글 삭제 */
    @DeleteMapping(value = "/board/detail/{boardNo}")
    public String delete(@PathVariable Long boardNo, @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }

        try {
            freeBoardService.deletePost(boardNo,user);
            model.addAttribute("successMessage", "게시글이 삭제되었습니다.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "게시글 삭제에 실패했습니다.");
        }
        return "redirect:/board/list";
    }
}

