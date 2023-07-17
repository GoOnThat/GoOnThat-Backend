package com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.vo;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.Comment;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Embeddable
@Getter
@EqualsAndHashCode
public class CommentParent {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "COMMENT_PARENT_NO")
    private Comment parent;
}
