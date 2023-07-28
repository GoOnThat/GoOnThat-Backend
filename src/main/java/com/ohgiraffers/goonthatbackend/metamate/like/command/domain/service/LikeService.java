package com.ohgiraffers.goonthatbackend.metamate.like.command.domain.service;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.repository.FreeBoardCommentRepository;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUserRepository;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardPostRepository;
import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.LikeBoardDTO;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.LikeCommentDTO;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity.Like;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.repository.LikeRepository;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final FreeBoardPostRepository freeBoardPostRepository;
    private final FreeBoardCommentRepository freeBoardCommentRepository;
    private final MetaUserRepository metaUserRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public List<LikeBoardDTO> addLike(FreeBoardPost freeBoardPost, LikeBoardDTO likeBoardDTO, SessionMetaUser user) {

        MetaUser metaUser = metaUserRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 이미 해당 사용자가 좋아요를 눌렀는지 확인
        if (likeRepository.findByFreeBoardPostAndMetaUser(freeBoardPost, metaUser).isPresent()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        Like like = likeBoardDTO.toEntity(metaUser, freeBoardPost);

        likeRepository.save(like);

        List<LikeBoardDTO> likeList = new ArrayList<>();

        System.out.println("ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄹ");

        // 좋아요 수를 반환
        return likeList;
    }

    @Transactional
    public List<LikeBoardDTO> deleteLike(FreeBoardPost freeBoardPost, LikeBoardDTO likeBoardDTO, SessionMetaUser user) {
        MetaUser metaUser = metaUserRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));


        if (!likeRepository.findByFreeBoardPostAndMetaUser(freeBoardPost, metaUser).isPresent()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        Like like = likeBoardDTO.toEntity(metaUser, freeBoardPost);

        likeRepository.delete(like);

        List<LikeBoardDTO> likeList = new ArrayList<>();

        // 좋아요 수를 반환
        return likeList;
    }

    public List<LikeBoardDTO> getLikeCount(FreeBoardPost freeBoardPost) {
    List<Like> likes = likeRepository.findByFreeBoardPost(freeBoardPost);

        return getLikeCount(freeBoardPost);
}

}
