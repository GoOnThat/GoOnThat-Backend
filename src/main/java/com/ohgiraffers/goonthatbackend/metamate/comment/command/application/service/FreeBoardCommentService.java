package com.ohgiraffers.goonthatbackend.metamate.comment.command.application.service;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.dto.FreeBoardCommentReadDTO;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.dto.FreeBoardCommentWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.repository.FreeBoardCommentRepository;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUserRepository;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FreeBoardCommentService {

    private final FreeBoardCommentRepository freeBoardCommentRepository;
    private final MetaUserRepository metaUserRepository;

    @Transactional
    public List<FreeBoardCommentReadDTO> addComment(FreeBoardPost refBoardPost, FreeBoardCommentWriteDTO freeBoardCommentWriteDTO, SessionMetaUser user) {

        MetaUser metaUser = metaUserRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        FreeBoardComment freeBoardComment = freeBoardCommentWriteDTO.toEntity(metaUser, refBoardPost);
        freeBoardCommentRepository.save(freeBoardComment);

        List<FreeBoardComment> commentList = freeBoardCommentRepository.findByFreeBoardPost(refBoardPost);

        List<FreeBoardCommentReadDTO> commentDTOList = new ArrayList<>();

        for (FreeBoardComment comment : commentList) {
            FreeBoardCommentReadDTO commentDTO = new FreeBoardCommentReadDTO().fromEntity(comment);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}