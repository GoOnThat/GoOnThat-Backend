package com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Embeddable
@Getter
@EqualsAndHashCode
public class CommentWriter {

    @Column(name = "COMMENT_WRITER", nullable = false)
    private String commentMetaUser;

    protected CommentWriter() {

    }

    public CommentWriter(String commentMetaUser) {
        validateMemberName(commentMetaUser);
        this.commentMetaUser = commentMetaUser;
    }

    private void validateMemberName(String commentMetaUser) {

        if (commentMetaUser == null) {
            throw new IllegalArgumentException("회원정보가 없습니다.");
        } else if (commentMetaUser.equals("")) {
            throw new IllegalArgumentException("회원정보가 공백입니다.");
        }
    }
}
