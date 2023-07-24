//package com.ohgiraffers.goonthatbackend.metamate.like.command.domain.service;
//
//import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.repository.LikeMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Service
//public class FindLikeService {
//
//    private final LikeMapper likeMapper;
//
//    @Autowired
//    public FindLikeService(LikeMapper likeMapper) {
//        this.likeMapper = likeMapper;
//    }
//
//    public int findLikeByUserIdAndBoardId(Map<String, Long> parameter) {
//        Integer check = likeMapper.findLikeByUserIdAndBoardId(parameter);
//        return check;
//    }
//
//    public int findAllLike(long boardId) {
//        return likeMapper.findNumberOfLike(boardId);
//    }
//}
