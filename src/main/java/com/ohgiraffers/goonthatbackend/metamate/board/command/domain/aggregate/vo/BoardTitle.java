package com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class BoardTitle {

    @Column(name="BOARD_TITLE", length=100, nullable = false)
    private String boardTitleName;
    protected  BoardTitle(){

    }

    public BoardTitle(String boardTitleName){
        validateTitleName(boardTitleName);
        this.boardTitleName=boardTitleName;
    }

    private void validateTitleName(String boardTitleName) {

        if(boardTitleName==null){
            throw new IllegalArgumentException("제목을 입력해 주세요");
        }else if(boardTitleName==""){
            throw new IllegalArgumentException("제목을 입력해 주세요");
        }
    }
}
