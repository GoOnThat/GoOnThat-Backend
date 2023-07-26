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
//import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.LikeCommentDTO;
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

    @Transactional
    public List<LikeBoardDTO> addLike(FreeBoardPost freeBoardPost, LikeBoardDTO likeBoardDTO, SessionMetaUser user) {
        MetaUser metaUser = metaUserRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Like like = likeBoardDTO.toEntity(metaUser, freeBoardPost);
        likeRepository.save(like);

        List<Like> likeList = likeRepository.findByFreeBoardPost(freeBoardPost);

        List<LikeBoardDTO> likeBoardList = new ArrayList<>();

        for (Like lk : likeList) {
            LikeBoardDTO likeBoard = new LikeBoardDTO().fromEntity(lk);
            likeBoardList.add(likeBoard);
        }

        return likeBoardList;
    }

    @Transactional
    public List<LikeBoardDTO> deleteLike(FreeBoardPost freeBoardPost, LikeBoardDTO likeBoardDTO,
                                         SessionMetaUser user) {
        MetaUser metaUser = metaUserRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Like like = likeBoardDTO.toEntity(metaUser, freeBoardPost);
        likeRepository.delete(like);

        return deleteLike(freeBoardPost, likeBoardDTO, user);
    }
}
