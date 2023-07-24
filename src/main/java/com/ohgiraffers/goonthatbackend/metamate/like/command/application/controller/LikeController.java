package com.ohgiraffers.goonthatbackend.metamate.like.command.application.controller;
import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.service.FreeBoardCommentService;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardPostRepository;
import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.LikeBoardDTO;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity.Like;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.repository.LikeRepository;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.service.LikeService;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//@Api(value = "Like Controller", tags = "FreeBoardPost")
//@RestController
//@RequestMapping("/api")

@Controller
//@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final FreeBoardCommentService freeBoardCommentService;
    private final FreeBoardPostRepository freeBoardPostRepository;
    private final LikeRepository likeRepository;
    private final LikeService likeService;

//    private final PushLikeService pushLikeService;
//    private final LikeService likeService;
//
//    public LikeController(PushLikeService pushLikeService, LikeService likeService) {
//        this.pushLikeService = pushLikeService;
//        this.likeService = likeService;
//    }
//
//    @PostMapping("")
//    @ResponseBody
//    public Map<String, Integer> pushLike(Authentication authentication, @RequestParam long boardId) {
//        Map<String, Integer> map = new HashMap<>();
//        SessionMetaUser sessionMetaUser = (SessionMetaUser) authentication.getPrincipal();
//        pushLikeService.pushLike(likeService.saveData(sessionMetaUser.getId(), boardId));
//        map.put("numberOfLike", likeService.numberOfLikes(boardId));
//        return map;
//    }
//
//    @PostMapping("/cancel")
//    @ResponseBody
//    public Map<String, Integer> deleteLike(Authentication authentication, @RequestParam long boardId) {
//        Map<String, Integer> map = new HashMap<>();
//        SessionMetaUser sessionMetaUser = (SessionMetaUser) authentication.getPrincipal();
//        pushLikeService.cancelLike(likeService.saveData(sessionMetaUser.getId(), boardId));
//        map.put("numberOfLike", likeService.numberOfLikes(boardId));
//        return map;
//    }


//
//    public LikeController(final LikeService likeService) {
//        this.likeService = likeService;
//    }
//
//    @ApiOperation(value = "게시글 좋아요", notes = "사용자가 게시글 좋아요를 누릅니다.")
//    @PostMapping("/boards/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Response like(@ApiParam(value = "게시글 id", required = true) @PathVariable final Long likeNo,
//                              @JwtAuth final MetaUser metaUser) {
//        return Response.success(likeService.updateLikeBoard(likeNo, metaUser));
//    }

    @ResponseBody
    @PostMapping("/like/{lk}")
    public List<LikeBoardDTO> addLike(@PathVariable("lk") Long like,
                                      @LoginUser SessionMetaUser user, Model model,
                                      LikeBoardDTO likeBoardDTO) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        Like like1 = likeRepository.findById(like)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

//        Like like2 = likeRepository.findById(like)
//                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        List<LikeBoardDTO> likeList = likeService.addLike(like, likeBoardDTO, user);

        System.out.println("likeList = " + likeList);
        return likeList;
    }
}
