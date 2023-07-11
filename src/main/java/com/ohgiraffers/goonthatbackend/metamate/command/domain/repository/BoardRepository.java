package com.ohgiraffers.goonthatbackend.metamate.command.domain.repository;

import com.ohgiraffers.goonthatbackend.ideaassemble.board.command.domain.aggregate.entity.Board;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BoardRepository {

    List<Board> selectBoardList(Board board);
}
