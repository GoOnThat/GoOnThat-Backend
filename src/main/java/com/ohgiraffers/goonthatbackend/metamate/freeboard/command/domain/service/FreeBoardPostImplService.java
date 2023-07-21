package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.service;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUserRepository;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.AccessService;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.FreeBoardPostService;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardPostRepository;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FreeBoardPostImplService implements FreeBoardPostService {

    private final FreeBoardPostRepository freeBoardPostRepository;
    private final MetaUserRepository metaUserRepository;
    private final AccessService accessControl;

    @Transactional
    @Override
    public void savePost(FreeBoardWriteDTO boardDTO, SessionMetaUser user) {

        MetaUser metaUser = metaUserRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        FreeBoardPost freeBoardPost = boardDTO.toEntity(metaUser);
        freeBoardPostRepository.save(freeBoardPost);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FreeBoardListDTO> getAllPosts() {
        List<FreeBoardPost> allPosts = freeBoardPostRepository.findByBoardIsDeletedFalse();
        List<FreeBoardListDTO> postList = new ArrayList<>();

        for (FreeBoardPost boardPost : allPosts) {
            FreeBoardListDTO freeBoardListDTO = FreeBoardListDTO.fromEntity(boardPost);
            postList.add(freeBoardListDTO);
        }

        return postList;
    }

    @Transactional(readOnly = true)
    @Override
    public FreeBoardDetailDTO getDetailPosts(Long boardNo) {
        FreeBoardPost boardPost = freeBoardPostRepository.findById(boardNo)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        List<FreeBoardComment> commentList = boardPost.getCommentList();

        return new FreeBoardDetailDTO().fromEntity(boardPost, commentList);
    }


    @Transactional
    @Override
    public void updatePost(Long boardNo, FreeBoardWriteDTO boardDTO, SessionMetaUser user) {
        FreeBoardPost boardPost = freeBoardPostRepository.findById(boardNo)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        boardPost.update(boardDTO.getBoardCategory(), boardDTO.getBoardTitle(), boardDTO.getBoardContent());

        freeBoardPostRepository.save(boardPost);
    }

    @Transactional
    @Override
    public void deletePost(Long boardNo, SessionMetaUser user) {
        FreeBoardPost boardPost = freeBoardPostRepository.findById(boardNo).orElseThrow(()->
                        new CustomException(ErrorCode.POST_NOT_FOUND));

        accessControl.validateUserAccess(boardPost, user);

        boardPost.delete();
    }
}