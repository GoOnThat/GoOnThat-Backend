package com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity.Like;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class LikeBoardDTO {

    private Long likeNo;
    private FreeBoardPost freeBoardPost;
    private String likeMan;
    private int likeCount;
//    private boolean likeStatus;

    public Like toEntity(MetaUser user, FreeBoardPost freeBoardPost) {
        return Like.builder()
                .freeBoardPost(freeBoardPost)
                .metaUser(user)
//                .likeStatus(true)
                .build();

    }

    public static LikeBoardDTO fromEntity(Like like) {

        return new LikeBoardDTO(
                like.getLikeNo(),
                like.getFreeBoardPost(),
                like.getMetaUser().getNickname(),
                1
        );
    }
}
