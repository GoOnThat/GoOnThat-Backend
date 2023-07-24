//package com.ohgiraffers.goonthatbackend.metamate.like.command.domain.service;
//
//import com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto.LikeDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Service
//public class LikeService {
//
//    private final CheckLikeService checkLikeService;
//
//    @Autowired
//    public LikeService(CheckLikeService checkLikeService) {
//        this.checkLikeService = checkLikeService;
//    }
//
//    public LikeDTO saveData(long userId, long boardId) {
//        return new LikeDTO(userId, boardId);
//    }
//
//    public boolean checkLike(Map<String, Long> parameter) {
//        return checkLikeService.checkLikeByUserIdAndBoardId(parameter) > 0;
//    }
//
//    public int numberOfLikes(long boardId) {
//        return checkLikeService.numberOfLike(boardId);
//    }
//}
