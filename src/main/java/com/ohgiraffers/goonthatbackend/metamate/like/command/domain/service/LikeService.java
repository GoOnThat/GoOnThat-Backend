package com.ohgiraffers.goonthatbackend.metamate.like.command.domain.service;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.LikeDTO;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.repository.FreeBoardCommentRepository;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUserRepository;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardPostRepository;
import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.LikeBoardDTO;
import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.LikeCommentDTO;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity.Like;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.repository.LikeRepository;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final FreeBoardPostRepository freeBoardPostRepository;
    private final FreeBoardCommentRepository freeBoardCommentRepository;
    private final MetaUserRepository metaUserRepository;
    private final LikeRepository likeRepository;

//    private final LikeRepository likeRepository;
//
//    @Autowired
//    public PushLikeService(LikeRepository likeRepository) {
//        this.likeRepository = likeRepository;
//    }
//
//    public void pushLike(LikeDTO likeDTO) {
//        Like like = new Like(likeDTO.getUserId(), likeDTO.getBoardId());
//        likeRepository.save(like);
//    }
//
//    @Transactional
//    public void cancelLike(LikeDTO likeDTO) {
//        likeRepository.deleteByLike(likeDTO.getUserId(), likeDTO.getBoardId());
//    }

//    private static final String SUCCESS_LIKE_BOARD = "좋아요 완료";
//    private static final String SUCCESS_UNLIKE_BOARD = "좋아요 취소";

//

//    @Transactional
//    public String updateLikeBoard(final Long likeNo, final MetaUser metaUser) {
//        FreeBoardPost freeBoardPost = freeBoardPostRepository.findById(likeNo)
//                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
//
//        if (!hasLikeBoard(freeBoardPost, metaUser)) {
//            freeBoardPost.increaseLikeCount();
//            return createLikeBoard(freeBoardPost, metaUser);
//        }
//
//        freeBoardPost.decreaseLikeCount();
//        return removeLikeBoard(freeBoardPost, metaUser);
//    }
//
//    private String removeLikeBoard(final FreeBoardPost freeBoardPost, final MetaUser metaUser) {
//        Like like = likeRepository.findByBoardAndUser(freeBoardPost, metaUser)
//                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
//
//        likeRepository.delete(like);
//
//        return SUCCESS_UNLIKE_BOARD;
//    }
//
//    public String createLikeBoard(final FreeBoardPost freeBoardPost, final MetaUser metaUser) {
//        Like like = new Like(freeBoardPost, metaUser);
//        likeRepository.save(like);
//        return SUCCESS_LIKE_BOARD;
//    }
//
//    public boolean hasLikeBoard(final FreeBoardPost freeBoardPost, final MetaUser metaUser) {
//        return likeRepository.findByBoardAndUser(freeBoardPost, metaUser)
//                .isPresent();
//    }
//
////    public List<LikeBoardDTO> addLike(Long like, LikeBoardDTO likeBoardDTO, SessionMetaUser user) {
////        return addLike(like, likeBoardDTO, user);
////    }
//
//    @Transactional
//    public String like(Long likeNo) {
//        FreeBoardPost freeBoardPost = freeBoardPostRepository.findById(likeNo)
//                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        MetaUser metaUser = metaUserRepository.findByNickname(authentication.getName());
//
//        Like like = null;
//        if (likeRepository.findByBoardAndUser(freeBoardPost, metaUser) == null) {
//
//            freeBoardPost.setLiked(freeBoardPost.getLiked() + 1);
//            like = new Like(freeBoardPost, metaUser);
//            likeRepository.save(like);
//            return "좋아요 완료";
//        } else {
//            MetaUser like1 = likeRepository.findByBoardAndUser(freeBoardPost, metaUser);
//            like.unLikeBoard(freeBoardPost);
//            likeRepository.delete(like);
//            return "좋아요 취소";
//        }
//    }


    @Transactional
    public List<LikeBoardDTO> addLike(FreeBoardPost freeBoardPost, FreeBoardComment freeBoardComment,
                                      LikeBoardDTO likeBoardDTO, LikeCommentDTO likeCommentDTO, SessionMetaUser user) {
        MetaUser metaUser = metaUserRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Like like = likeBoardDTO.toEntity(metaUser, freeBoardPost);
        likeRepository.save(like);
//        Like like2 = likeCommentDTO.toEntity(metaUser, freeBoardComment);
//        likeRepository.save(like2);

        List<Like> likeList = likeRepository.findByFreeBoardPost(freeBoardPost);
//        List<Like> likeList2 = likeRepository.findByFreeBoardComment(freeBoardComment);

        List<LikeBoardDTO> likeBoardList = new ArrayList<>();
//        List<LikeCommentDTO> likeDTOList2 = new ArrayList<>();

        for (Like lk : likeList) {
            LikeBoardDTO likeBoard = new LikeBoardDTO();
            likeBoardList.add(likeBoard);
        }

//        for (Like like2 : likeList2) {
//            LikeCommentDTO commentDTO = new LikeCommentDTO();
//            likeDTOList2.add(likeCommentDTO);
//        }

        return likeBoardList;
    }

    public List<LikeBoardDTO> addLike(Long like, LikeBoardDTO likeBoardDTO, SessionMetaUser user) {
        return addLike(like, likeBoardDTO, user);
    }
}
