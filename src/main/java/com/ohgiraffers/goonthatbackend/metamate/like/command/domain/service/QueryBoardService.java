//package com.ohgiraffers.goonthatbackend.metamate.like.command.domain.service;
//import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.CheckBoardDTO;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity.QueryBoard;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class QueryBoardService {
//
//    private final CheckUserService checkUserService;
//
//    @Autowired
//    public QueryBoardService(CheckUserService checkUserService) {
//        this.checkUserService = checkUserService;
//    }
//
//    public List<CheckBoardDTO> saveInfo(List<QueryBoard> queryBoards) {
//        List<CheckBoardDTO> boardDTOS = new ArrayList<>();
//
//        for (QueryBoard board : queryBoards) {
//            CheckBoardDTO checkBoardDTO = new CheckBoardDTO(board);
//            boardDTOS.add(checkBoardDTO);
//        }
//
//        return boardDTOS;
//    }
//
//    public boolean metaUser(long userId, long boardUserId) {
//        return userId == boardUserId;
//    }
//
//    public String write(long userId) {
//        return checkUserService.findUserNameByUserId(userId);
//    }
//
//    public MetaUser metaUser(long userId) {
//        return checkUserService.findById(userId);
//    }
//}
