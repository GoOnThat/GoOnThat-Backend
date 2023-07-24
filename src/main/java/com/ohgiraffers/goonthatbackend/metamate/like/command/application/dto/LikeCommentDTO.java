package com.ohgiraffers.goonthatbackend.metamate.like.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class LikeCommentDTO {

    private Long likeNo;
    private FreeBoardComment freeBoardComment;
    private MetaUser metaUser;
    private boolean likeStatus;

    public Like toEntity(MetaUser user, FreeBoardComment freeBoardComment) {
        return Like.builder()
//                .freeBoardComment(this.freeBoardComment)
                .metaUser(user)
//                .likeStatus(true)
                .build();
    }
}
