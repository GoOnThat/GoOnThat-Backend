package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.service;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.dto.FreeBoardCommentReadDTO;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.repository.FreeBoardCommentRepository;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUserRepository;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.multifile.command.application.dto.MultiFilesReadDTO;
import com.ohgiraffers.goonthatbackend.metamate.multifile.command.application.dto.MultiFilesWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.multifile.command.domain.aggregate.entity.MultiFiles;
import com.ohgiraffers.goonthatbackend.metamate.multifile.command.infra.repository.MultiFilesRepository;
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
    private final MultiFilesRepository multiFilesRepository;
    private final AccessService accessService;

    //게시글 쓰기
    @Transactional
    @Override
    public void savePost(FreeBoardWriteDTO freeBoardWriteDTO,
                         List<MultiFilesWriteDTO> multiFilesWriteDTOList,
                         SessionMetaUser user) {
        //유저정보 조회
        MetaUser metaUser = metaUserRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //게시판글 저장
        FreeBoardPost freeBoardPost = freeBoardWriteDTO.toEntity(metaUser);
        freeBoardPostRepository.save(freeBoardPost);

        // 파일 업로드가 있을 경우 파일 정보 저장
        if (multiFilesWriteDTOList != null) {
            List<MultiFiles> multiFiles = new ArrayList<>();
            for (MultiFilesWriteDTO multiFilesWriteDTO : multiFilesWriteDTOList) {
                MultiFiles multiFile = multiFilesWriteDTO.toEntity();
                multiFile.setFreeBoardPost(freeBoardPost);
                multiFiles.add(multiFile);
            }
            multiFilesRepository.saveAll(multiFiles);
        }
    }

    //전체 조회
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

    //키워드별 검색
    @Transactional(readOnly = true)
    @Override
    public Page<FreeBoardListDTO> getSearchPosts(String key, String searchKeyword, Pageable pageable) {

        Page<FreeBoardPost> allPosts;
        List<FreeBoardListDTO> postList = new ArrayList<>();

        switch (key) {
            case "title":
                allPosts = freeBoardPostRepository.findByBoardTitleContainingAndBoardIsDeletedFalse(searchKeyword, pageable);
                break;
            case "content":
                allPosts = freeBoardPostRepository.findByBoardContentContainingAndBoardIsDeletedFalse(searchKeyword, pageable);
                break;
            case "writer":
                allPosts = freeBoardPostRepository.findByMetaUserNicknameContainingAndBoardIsDeletedFalse(searchKeyword, pageable);
                break;
            default:
                allPosts = freeBoardPostRepository.findByBoardIsDeletedFalse(pageable);
                break;
        }
        for (FreeBoardPost boardPost : allPosts) {
            FreeBoardListDTO freeBoardListDTO = FreeBoardListDTO.fromEntity(boardPost);
            postList.add(freeBoardListDTO);
        }

        return new PageImpl<>(postList, pageable, allPosts.getTotalElements());
    }


    //글번호별 세부조회
    @Transactional(readOnly = true)
    @Override
    public FreeBoardDetailDTO getDetailPosts(Long boardNo) {
        //게시글 조회 로직
        FreeBoardPost boardPost = freeBoardPostRepository.findById(boardNo)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //파일 조회 로직
        List<MultiFiles> fileList = multiFilesRepository.findByFreeBoardPost_BoardNo(boardNo);
        List<MultiFilesReadDTO> multiFilesReadDTOList = new ArrayList<>();
        for (MultiFiles file : fileList) {
            MultiFilesReadDTO fileDTO = MultiFilesReadDTO.fromEntity(file);
            multiFilesReadDTOList.add(fileDTO);
        }

        //댓글 조회 로직
        List<FreeBoardComment> commentList = freeBoardCommentRepository.findByFreeBoardPost_BoardNoAndCommentIsDeletedFalse(boardNo);
        List<FreeBoardCommentReadDTO> commentReadList = new ArrayList<>();
        for (FreeBoardComment comment : commentList) {
            FreeBoardCommentReadDTO freeBoardComment = FreeBoardCommentReadDTO.fromEntity(comment);
            freeBoardComment.setCommentIsDeleted(comment.isCommentIsDeleted());
            commentReadList.add(freeBoardComment);
        }

        return new FreeBoardDetailDTO().fromEntity(boardPost, commentReadList, multiFilesReadDTOList);
    }

    //조회수
    @Transactional
    @Override
    public void hitsUp(Long boardNo, FreeBoardDetailDTO freeBoardDetailDTO) {
        FreeBoardPost boardPost = freeBoardPostRepository.findById(boardNo)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        boardPost.hitsUp(freeBoardDetailDTO.getBoardHits());
    }

    //게시글 수정 적용
    @Transactional
    @Override
    public void updatePost(Long boardNo, FreeBoardEditDTO freeBoardEditDTO, SessionMetaUser user) {

        FreeBoardPost boardPost = freeBoardPostRepository.findById(boardNo)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!accessService.postValidateUserAccess(boardPost, user)) {
            throw new IllegalStateException("권한이 없습니다.");
        }
        boardPost.update(
                freeBoardEditDTO.getBoardCategory(),
                freeBoardEditDTO.getBoardTitle(),
                freeBoardEditDTO.getBoardContent()
        );
    }

    //게시글 삭제 (SOFT DELETE)
    @Transactional
    @Override
    public void deletePost(Long boardNo, SessionMetaUser user) {

        FreeBoardPost boardPost = freeBoardPostRepository.findById(boardNo).orElseThrow(() ->
                new CustomException(ErrorCode.POST_NOT_FOUND));

        if (!accessService.postValidateUserAccess(boardPost, user)) {
            throw new IllegalStateException("권한이 없습니다.");
        }
        boardPost.delete();
    }
}