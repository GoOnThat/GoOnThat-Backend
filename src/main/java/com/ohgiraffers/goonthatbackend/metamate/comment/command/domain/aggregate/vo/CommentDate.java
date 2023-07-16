package com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
@EqualsAndHashCode
public class CommentDate {

    @CreatedDate
    @Column(name="COMMNET_CREATE_DATE",nullable = false )
    private LocalDate commentCreateDate;

    @LastModifiedDate
    @Column(name="COMMENT_MODIFIED_DATE",nullable = false)
    private LocalDate commentModifiedDate;

    protected CommentDate(){}

    //등록
    public CommentDate(LocalDate commentCreateDate, LocalDate commentModifiedDate) {
        validateIsPast(commentCreateDate);
        validateIsPast(commentModifiedDate);
        this.commentCreateDate = commentCreateDate;
        this.commentModifiedDate = commentModifiedDate;
    }
    //수정
    public CommentDate(LocalDate commentModifiedDate) {
        validateIsPast(commentModifiedDate);
        this.commentModifiedDate = commentModifiedDate;
    }

    private void validateIsPast(LocalDate date) {

        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("작성이 미래일 수 없습니다.");
        }
    }

}