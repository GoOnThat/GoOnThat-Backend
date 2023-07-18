package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.service;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.FreeBoardService;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class FreeBoardImplService implements FreeBoardService {

    private final FreeBoardRepository repository;
    @Autowired
    public FreeBoardImplService(FreeBoardRepository repository) {
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

        List<FreeBoardPost> allPosts = repository.findAll();
        List<FreeBoardListDTO> postList =new ArrayList<>();

        for(FreeBoardPost boardPost : allPosts) {
            FreeBoardListDTO freeBoardListDTO=new FreeBoardListDTO().fromEntity(boardPost);
            postList.add(freeBoardListDTO);
        }
        return postList;
    }
//
//    public FreeBoardDetailDTO detailBoard(Long boardNo) {
//        return null;
//    }
//
//    public FreeBoardDetailDTO detailBoard(Long boardNo) {
//        FreeBoard freeBoard = freeBoardRepository.findById(boardNo).orElse(null);
//
//        FreeBoardDetailDTO board = FreeBoardDetailDTO.entityToDTO(freeBoard);
//        if (freeBoard != null) {
//            return board;
//        } else {
//            throw new RuntimeException("해당 게시물을 찾을 수 없습니다.");
//        }
//
//    }
}