package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoard;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.vo.BoardDeleteYn;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardRepository;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.service.mapper.FreeBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreeBoardService {

    private final FreeBoardRepository freeBoardRepository;
    private final FreeBoardMapper freeBoardMapper;

    @Autowired
    public FreeBoardService(FreeBoardRepository freeBoardRepository, FreeBoardMapper freeBoardMapper) {
        this.freeBoardMapper = freeBoardMapper;
        this.freeBoardRepository = freeBoardRepository;
    }

    public void write(FreeBoardWriteDTO freeBoardWrite) {

        FreeBoard freeBoard = freeBoardMapper.toFreeBoardWrite(freeBoardWrite);


        freeBoardRepository.save(freeBoard);
    }

    public void enrolledwrite(FreeBoardDetailDTO freeBoardDetailDTO) {

        FreeBoard freeBoard = freeBoardMapper.toFreeBoardDetail(freeBoardDetailDTO);

        freeBoardRepository.save(freeBoard);
    }

    public List<FreeBoard> getAllBoards(FreeBoardListDTO freeBoardListDTO) {

        List<FreeBoard> boards = freeBoardRepository.findAll();

        return boards;
    }

    public FreeBoardDetailDTO detailBoard(Long boardNo) {
        FreeBoard freeBoard = freeBoardRepository.findById(boardNo).orElse(null);

        FreeBoardDetailDTO board = FreeBoardDetailDTO.entityToDTO(freeBoard);
        System.out.println(freeBoardMapper.boardDetailDTO(freeBoard));
        if (freeBoard != null) {
            return board;
        } else {
            throw new RuntimeException("해당 게시물을 찾을 수 없습니다.");
        }

    }
}