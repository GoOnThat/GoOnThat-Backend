package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.service;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.dto.FreeBoardCommentReadDTO;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.repository.FreeBoardCommentRepository;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUserRepository;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.file.command.domain.aggregate.entity.File;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardEditDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.AccessService;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.FreeBoardPostService;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardPostRepository;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FreeBoardPostImplService implements FreeBoardPostService {

    private final FreeBoardPostRepository freeBoardPostRepository;
    private final FreeBoardCommentRepository freeBoardCommentRepository;
    private final MetaUserRepository metaUserRepository;
    private final AccessService accessService;

    @Transactional
    @Override
    public String savePost(FreeBoardWriteDTO boardDTO, SessionMetaUser user) {

        MetaUser metaUser = metaUserRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        FreeBoardPost freeBoardPost = boardDTO.toEntity(metaUser);
        freeBoardPostRepository.save(freeBoardPost);
        return "게시글이 등록되었습니다.";
    }

    @Transactional(readOnly = true)
    @Override
    public Page<FreeBoardListDTO> getAllPosts(Pageable pageable) {
        Page<FreeBoardPost> allPosts = freeBoardPostRepository.findByBoardIsDeletedFalse(pageable);
        List<FreeBoardListDTO> postList = new ArrayList<>();


        for (FreeBoardPost boardPost : allPosts) {
            FreeBoardListDTO freeBoardListDTO = FreeBoardListDTO.fromEntity(boardPost);
            postList.add(freeBoardListDTO);
        }

        return new PageImpl<>(postList, pageable, allPosts.getTotalElements());
    }

    @Transactional(readOnly = true)
    @Override
    public FreeBoardDetailDTO getDetailPosts(Long boardNo) {
        //게시글 조회 로직
        FreeBoardPost boardPost = freeBoardPostRepository.findById(boardNo)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));


        //댓글 조회 로직
        List<FreeBoardComment> commentList = freeBoardCommentRepository.findByFreeBoardPost_BoardNo(boardNo);
        List<FreeBoardCommentReadDTO> commentRead= new ArrayList<>();
        for (FreeBoardComment comment : commentList) {
            FreeBoardCommentReadDTO freeBoardComment = FreeBoardCommentReadDTO.fromEntity(comment);
            freeBoardComment.setCommentIsDeleted(comment.isCommentIsDeleted());
            commentRead.add(freeBoardComment);
        }




        return new FreeBoardDetailDTO().fromEntity(boardPost, commentRead);
    }

    @Transactional
    @Override
    public void hitsUp(Long boardNo, FreeBoardDetailDTO freeBoardDetailDTO){
        FreeBoardPost boardPost = freeBoardPostRepository.findById(boardNo)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        boardPost.hitsUp(freeBoardDetailDTO.getBoardHits());
    }



    @Transactional
    @Override
    public String updatePost(Long boardNo, FreeBoardEditDTO freeBoardEditDTO, SessionMetaUser user) {

        FreeBoardPost boardPost = freeBoardPostRepository.findById(boardNo)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!accessService.postValidateUserAccess(boardPost, user)) {
            return "권한이 없습니다.";
        }

        boardPost.update(
                freeBoardEditDTO.getBoardCategory()
                , freeBoardEditDTO.getBoardTitle()
                , freeBoardEditDTO.getBoardContent()
        );
        freeBoardPostRepository.save(boardPost);

        return  "게시글이 수정되었습니다.";
    }

    @Transactional
    @Override
    public String deletePost(Long boardNo, SessionMetaUser user) {

        FreeBoardPost boardPost = freeBoardPostRepository.findById(boardNo).orElseThrow(()->
                        new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!accessService.postValidateUserAccess(boardPost, user)) {
            return "권한이 없습니다.";
        }
        boardPost.delete();

        return "게시글이 삭제되었습니다.";
    }
}