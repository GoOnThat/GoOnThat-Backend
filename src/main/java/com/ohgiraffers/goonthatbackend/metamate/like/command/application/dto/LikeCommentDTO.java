//package com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto;
//
//import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
//import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
//import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity.Like;
//import lombok.*;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//public class LikeCommentDTO {
//
//    private Long likeNo;
//    private FreeBoardComment freeBoardComment;
//    private MetaUser metaUser;
//
//
//    public Like toEntity(MetaUser user, FreeBoardComment freeBoardComment) {
//        return Like.builder()
//                .freeBoardComment(freeBoardComment)
//                .metaUser(user)
//                .build();
//    }
//
//    public static LikeBoardDTO fromEntity(Like like) {
//
//        return new LikeBoardDTO(
//                like.getLikeNo(),
//                like.getFreeBoardPost(),
//                like.getMetaUser().getNickname()
//        );
//    }
//}
