package com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class BoardContent {

    @Column(name="BOARD_CONTENT", columnDefinition = "TEXT",nullable = false)
    private String boardContentText;

    protected BoardContent(){

    }

    public BoardContent(String boardContentText){
        validateBoardContentText(boardContentText);
        this.boardContentText=boardContentText;
    }

    private void validateBoardContentText(String boardContentText) {

        if(boardContentText==null){
            throw new IllegalArgumentException("내용을 입력해 주세요");
        }else if(boardContentText==""){
            throw new IllegalArgumentException("내용을 입력해 주세요");
        }

    }
}
