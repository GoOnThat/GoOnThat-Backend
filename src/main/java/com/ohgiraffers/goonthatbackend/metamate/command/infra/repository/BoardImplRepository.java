package com.ohgiraffers.goonthatbackend.metamate.command.infra.repository;

import com.ohgiraffers.goonthatbackend.ideaassemble.board.command.domain.aggregate.entity.Board;
import com.ohgiraffers.goonthatbackend.metamate.command.domain.repository.BoardRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public class BoardImplRepository implements BoardRepository {

    private final SqlSession sqlSession;

    @Autowired
    public BoardImplRepository(@Qualifier("SessionTemplate") SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Board> selectBoardList(Board board) {

        return sqlSession.selectList("BoardRepository.selectBoardList", board);

    }
}
