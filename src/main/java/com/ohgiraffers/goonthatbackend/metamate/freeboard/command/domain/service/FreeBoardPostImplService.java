package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.service;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.FreeBoardPostService;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FreeBoardPostImplService implements FreeBoardPostService {

    private final FreeBoardPostRepository repository;
    @Autowired
    public FreeBoardPostImplService(FreeBoardPostRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void savePost(FreeBoardWriteDTO boardDTO) {

       repository.save(boardDTO.toEntity());
    }
    @Transactional(readOnly = true)
    @Override
    public List<FreeBoardListDTO> getAllPosts() {

        List<FreeBoardPost> allPosts = repository.findByBoardIsDeleted(false);
        List<FreeBoardListDTO> postList =new ArrayList<>();

        for(FreeBoardPost boardPost : allPosts) {
            FreeBoardListDTO freeBoardListDTO=new FreeBoardListDTO().fromEntity(boardPost);
            postList.add(freeBoardListDTO);
        }
        return postList;
    }

    @Transactional
    @Override
    public FreeBoardDetailDTO getDetailPosts(Long boardNo) {

        Optional<FreeBoardPost> freeBoard = repository.findById(boardNo);
        FreeBoardDetailDTO board= new FreeBoardDetailDTO();
//        board.fromEntity(freeBoard);

        return board;

    }
}