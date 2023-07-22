package com.ohgiraffers.goonthatbackend.metamate.comment.command.application.controller;

import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.dto.FreeBoardCommentReadDTO;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.dto.FreeBoardCommentWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.service.FreeBoardCommentService;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardPostRepository;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FreeBoardCommentController {

    private final FreeBoardCommentService freeBoardCommentService;
    private final FreeBoardPostRepository freeBoardPostRepository;


        @GetMapping("/comment/{refBoardNo}")
        public String showBoardDetail(@PathVariable Long refBoardNo,
                                      @ModelAttribute("freeBoardCommentReadDTO") FreeBoardCommentReadDTO freeBoardCommentReadDTO,
                                      @LoginUser SessionMetaUser user, Model model) {

            if (user != null) {
                model.addAttribute("user", user);
            }

            // 댓글 목록 조회
            List<FreeBoardCommentReadDTO> commentList = freeBoardCommentService.getCommentList(refBoardNo);
            model.addAttribute("commentList", commentList);

            return "/comment/{refBoardNo}";
        }


    @ResponseBody
    @PostMapping("/comment/{refBoardNo}")
    public List<FreeBoardCommentWriteDTO> addComment(@PathVariable("refBoardNo") Long refBoardNo,
                                                     @LoginUser SessionMetaUser user, Model model,
                                                     FreeBoardCommentWriteDTO freeBoardCommentWriteDTO){


        if (user != null) {
            model.addAttribute("user", user);
        }
        FreeBoardPost refBoardPost = freeBoardPostRepository.findById(refBoardNo)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        List<FreeBoardCommentWriteDTO> commentList = freeBoardCommentService.addComment(refBoardPost, freeBoardCommentWriteDTO, user);

        System.out.println("commentList = " + commentList);
        return commentList;
    }

//    @PostMapping("/error")
//    @ResponseBody
//    public String error() {
//        return "error";
//    }
}
