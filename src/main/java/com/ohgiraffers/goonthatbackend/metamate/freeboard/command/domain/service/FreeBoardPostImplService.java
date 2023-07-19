package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.service;

import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUserRepository;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.FreeBoardPostService;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardPostRepository;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FreeBoardPostImplService implements FreeBoardPostService {

    private final FreeBoardPostRepository freeBoardPostRepository;
    private final MetaUserRepository metaUserRepository;

    @Override
    @Transactional
    public void savePost(FreeBoardWriteDTO boardDTO, SessionMetaUser user) {

//        MetaUser metaUser= MetaUser.builder()
//                                .orElseThrow(
//                                        () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
//                                );
//                FreeBoardPost freeBoardPost= boardDTO.toEntity(metaUser);
//                freeBoardPostRepository.save(freeBoardPost);

    }

    @Transactional(readOnly = true)
    @Override
    public List<FreeBoardListDTO> getAllPosts() {

        List<FreeBoardPost> allPosts = freeBoardPostRepository.findByBoardIsDeleted(false);
        List<FreeBoardListDTO> postList = new ArrayList<>();

        for (FreeBoardPost boardPost : allPosts) {
            FreeBoardListDTO freeBoardListDTO = new FreeBoardListDTO().fromEntity(boardPost);
            postList.add(freeBoardListDTO);
        }
        return postList;
    }

    @Transactional
    @Override
    public FreeBoardDetailDTO getDetailPosts(Long boardNo) {

        Optional<FreeBoardPost> freeBoard = freeBoardPostRepository.findById(boardNo);
        FreeBoardDetailDTO board = new FreeBoardDetailDTO();
//        board.fromEntity(freeBoard);

        return board;

    }
}