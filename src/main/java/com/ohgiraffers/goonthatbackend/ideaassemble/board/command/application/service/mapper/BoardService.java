package com.ohgiraffers.goonthatbackend.ideaassemble.board.command.application.service.mapper;

import com.ohgiraffers.goonthatbackend.ideaassemble.board.command.application.dto.BoardListDTO;
import com.ohgiraffers.goonthatbackend.ideaassemble.board.command.domain.aggregate.entity.Board;
import com.ohgiraffers.goonthatbackend.ideaassemble.board.command.domain.repository.BoardRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mybatis.spring.SqlSessionUtils.getSqlSession;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

//    public List<Board> selectBoardList(BoardListDTO boardListDTO) {
//
////        List<BoardListDTO> boardList = boardRepository.selectBoardList(boardListDTO.getBoardNo());
//    }
//}
}