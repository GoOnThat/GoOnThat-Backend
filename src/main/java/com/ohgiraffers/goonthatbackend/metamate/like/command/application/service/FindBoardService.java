//package com.ohgiraffers.goonthatbackend.metamate.like.command.application.service;
//
//import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.CheckBoardDTO;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity.QueryBoard;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.service.QueryBoardService;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.infra.repository.BoardMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FindBoardService {
//
//    private final BoardMapper boardMapper;
//    private final QueryBoardService queryBoardService;
//
//    @Autowired
//    public FindBoardService(BoardMapper boardMapper, QueryBoardService queryBoardService) {
//        this.boardMapper = boardMapper;
//        this.queryBoardService = queryBoardService;
//    }
//
//    public List<CheckBoardDTO> findAllBoard() {
//        List<QueryBoard> queryBoards = boardMapper.findAllBoards();
//        List<CheckBoardDTO> checkBoardDTOS = queryBoardService.saveInfo(queryBoards);
//        return checkBoardDTOS;
//    }
//
//    public QueryBoard findBoardById(Long boardId) {
//        QueryBoard queryBoard = boardMapper.findBoardById(boardId);
//        return queryBoard;
//    }
//
//    public long findBoardUserId(long boardId) {
//        long boardUserId = boardMapper.findBoardUserIdByBoardId(boardId);
//        return boardId;
//    }
//}
