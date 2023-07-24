//package com.ohgiraffers.goonthatbackend.metamate.like.command.infra.service;
//
//import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.service.FindLikeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Service
//public class CheckLikeService implements com.ohgiraffers.goonthatbackend.metamate.like.command.domain.service.CheckLikeService{
//
//    private final FindLikeService findLikeService;
//
//    @Autowired
//    public CheckLikeService(FindLikeService findLikeService) {
//        this.findLikeService = findLikeService;
//    }
//
//    public int checkLikeByUserIdAndBoardId(Map<String, Long> parameter) {
//        int result = findLikeService.findLikeByUserIdAndBoardId(parameter);
//        return result;
//    }
//
//    public int numberOfLike(long boardId) {
//        return findLikeService.findAllLike(boardId);
//    }
//}
