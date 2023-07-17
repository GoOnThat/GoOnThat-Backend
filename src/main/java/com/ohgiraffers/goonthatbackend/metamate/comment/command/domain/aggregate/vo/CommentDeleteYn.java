package com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class CommentDeleteYn {

    @Column(name = "COMMENT_DELETE_YN", nullable = false)
    private String commentDeleteYn;

    public CommentDeleteYn() {
    }

    public CommentDeleteYn(String commentDeleteYn) {
        validateBoardDeleteYn(commentDeleteYn);
        this.commentDeleteYn = commentDeleteYn;
    }

    private void validateBoardDeleteYn(String commentDeleteYn) {
        if (commentDeleteYn == null) {
            throw new IllegalArgumentException("값을 입력해 주세요");
        } else if (commentDeleteYn.equals("")) {
            throw new IllegalArgumentException("공백을 입력할수 없습니다.");
        } else if (!(commentDeleteYn.toUpperCase().equals("Y") || commentDeleteYn.toUpperCase().equals("N"))) {
            throw new IllegalArgumentException("잘못된 값 입니다.");
        }
    }
}
