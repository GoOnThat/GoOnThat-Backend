package com.ohgiraffers.goonthatbackend.metamate.like.command.application.controller;
import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.service.FreeBoardCommentService;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardPostRepository;
import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.LikeBoardDTO;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.vo.LikeVO;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.repository.LikeRepository;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.service.LikeService;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.infra.service.IfreeBoardService;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller

@RequiredArgsConstructor
public class LikeController {

    private final FreeBoardCommentService freeBoardCommentService;
    private final FreeBoardPostRepository freeBoardPostRepository;
    private final LikeRepository likeRepository;
    private final LikeService likeService;

//    @Autowired
//    private IfreeBoardService service;
//
//    @GetMapping("/freeDetail")
//    public void freeDetail(int freeboard_no, MetaUser metaUser, Model model) {
//
//        System.out.println("상세보기 페이지");
//        model.addAttribute("Detail", service.freeDetail(freeboard_no));
//
//        LikeVO like = new LikeVO();
//
//        like.setBoard_no(freeboard_no);
//        like.setMetaUser(metaUser);
//        like.setLike_type(1);
//        model.addAttribute("like", service.findLike(freeboard_no, String.valueOf(metaUser)));
//        model.addAttribute("getLike", service.getLike(freeboard_no, 1));
//        service.hit(freeboard_no);
//    }
//        @ResponseBody
//        @PostMapping("/likeUp")
//        public void likeup(@RequestBody LikeVO vo) {
//            System.out.println("컨트롤러 연결 성공");
//            System.out.println(vo.getBoard_no());
//            System.out.println(vo.getMetaUser());
//            System.out.println(vo.getLike_type());
//            service.likeUp(vo.getBoard_no(), String.valueOf(vo.getMetaUser()), vo.getLike_type());
//
//        }
//
//        @ResponseBody
//        @PostMapping("/likeDown")
//        public void likeDown(@RequestBody LikeVO vo) {
//            System.out.println("컨트롤러 연결 성공");
//            System.out.println(vo.getBoard_no());
//            System.out.println(vo.getMetaUser());
//            System.out.println(vo.getLike_type());
//            service.likeDown(vo.getBoard_no(), String.valueOf(vo.getMetaUser()),vo.getLike_type());
//        }

    @ResponseBody
    @PostMapping("/like/{boardNo}")
    public List<LikeBoardDTO> addLike(@PathVariable("boardNo") Long boardNo,
                                      @LoginUser SessionMetaUser user, Model model,
                                      LikeBoardDTO likeBoardDTO) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        FreeBoardPost freeBoardPost = freeBoardPostRepository.findById(boardNo)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));


        // 업데이트된 좋아요 수를 포함한 좋아요 목록을 반환
        List<LikeBoardDTO> likeList = likeService.addLike(freeBoardPost, likeBoardDTO, user);
        List<LikeBoardDTO> deleteLike = likeService.deleteLike(freeBoardPost, likeBoardDTO, user);
        /*
        문제는 이새끼임...
        그동안 insert 안됐던건 transactional annotation 때문에 에러가 발생한 경우 디비에 저장을 안해서 발생한 것임
        고로 위에있는 새기처리해야됨...
         */

        return likeList;
    }

    @ResponseBody
    @PostMapping("/delete/{boardNo}")
    public List<LikeBoardDTO> deleteLike(@PathVariable("boardNo") Long likeNo,
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
