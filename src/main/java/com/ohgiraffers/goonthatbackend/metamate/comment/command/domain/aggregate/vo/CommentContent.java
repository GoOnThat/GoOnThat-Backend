package com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.vo;


import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class CommentContent {

    @Column(name="COMMENT_CONTENT", columnDefinition = "TEXT",nullable = false)
    private String commentContent;

    public CommentContent(){}
    public CommentContent(String commentContent){
        validateCommentContent(commentContent);
        this.commentContent=commentContent;
    }

    public void validateCommentContent(String commentContent){
        if(commentContent==null){
            throw new IllegalArgumentException("내용을 입력해 주세요");
        }else if(commentContent.equals("")){
            throw new IllegalArgumentException("내용이 공백입니다.");
        }
    }
}