package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.controller;

import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardEditDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.FreeBoardPostService;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
        FreeBoardDetailDTO boardDetail = freeBoardService.getDetailPosts(boardNo);
        if (boardDetail.isBoardIsDeleted()) {
            return "board/list";
        }

        model.addAttribute("boardNo", boardNo);

        model.addAttribute("boardDetail", freeBoardService.getDetailPosts(boardNo));

        return "board/detail";
    }

    /* 게시글 수정 페이지 조회 */
    @GetMapping("/edit/{boardNo}")
    public String edit(@PathVariable Long boardNo, @ModelAttribute("freeBoardEditDTO") FreeBoardEditDTO freeBoardEditDTO,
                       @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }

        FreeBoardDetailDTO boardDetail = freeBoardService.getDetailPosts(boardNo);
        if (boardDetail.getMetaUser().getId().equals(user.getId())) {
            model.addAttribute("boardDetail", boardDetail);
            freeBoardEditDTO.setBoardTitle(boardDetail.getBoardTitle());
            freeBoardEditDTO.setBoardContent(boardDetail.getBoardContent());
            freeBoardEditDTO.setBoardCategory(boardDetail.getBoardCategory());
            return "board/edit";
        } else {
            model.addAttribute("Message", "게시글을 수정할 권한이 없습니다.");
            return "redirect:/board/detail/" + boardNo;
        }
    }

    /* 게시글 수정 */
    @PostMapping("/edit/{boardNo}")
    public String editSave(@PathVariable Long boardNo, @ModelAttribute("freeBoardEditDTO") FreeBoardEditDTO freeBoardEditDTO,
                           @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }

        String message = freeBoardService.updatePost(boardNo, freeBoardEditDTO, user);
        model.addAttribute("Message", message);

        return "redirect:/board/detail/" + boardNo;
    }

    /* 게시글 삭제 */
    @PostMapping(value = "/detail/{boardNo}")
    public String delete(@PathVariable Long boardNo, @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        String message = freeBoardService.deletePost(boardNo, user);
        model.addAttribute("Message", message);

        if (message.equals("게시글이 삭제되었습니다.")) {
            return "redirect:/board/list";
        } else {
            return "redirect:/board/detail/" + boardNo;
        }

    }
}
