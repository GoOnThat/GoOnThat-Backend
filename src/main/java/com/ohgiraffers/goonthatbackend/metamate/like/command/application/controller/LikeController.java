package com.ohgiraffers.goonthatbackend.metamate.like.command.application.controller;
import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.service.FreeBoardCommentService;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardPostRepository;
import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.LikeBoardDTO;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.repository.LikeRepository;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.service.LikeService;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
//@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final FreeBoardCommentService freeBoardCommentService;
    private final FreeBoardPostRepository freeBoardPostRepository;
    private final LikeRepository likeRepository;
    private final LikeService likeService;

    @ResponseBody
    @PostMapping("/like")
    public List<LikeBoardDTO> addLike(@PathVariable("like") Long likeNo,
                                      @LoginUser SessionMetaUser user, Model model,
                                      LikeBoardDTO likeBoardDTO) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        FreeBoardPost freeBoardPost = freeBoardPostRepository.findById(likeNo)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        List<LikeBoardDTO> likeList = likeService.addLike(freeBoardPost, likeBoardDTO, user);

        return likeList;
    }

    @ResponseBody
    @PostMapping("/delete")
    public List<LikeBoardDTO> deleteLike(@PathVariable("delete") Long likeNo,
                                      @LoginUser SessionMetaUser user, Model model,
                                      LikeBoardDTO likeBoardDTO) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        FreeBoardPost freeBoardPost = freeBoardPostRepository.findById(likeNo)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));


        List<LikeBoardDTO> likeList = likeService.deleteLike(freeBoardPost, likeBoardDTO, user);

        System.out.println("likeList = " + likeList);

        return likeList;
    }
}
