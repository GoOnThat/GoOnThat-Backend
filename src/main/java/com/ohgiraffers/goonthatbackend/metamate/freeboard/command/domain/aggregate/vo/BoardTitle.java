package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class BoardTitle {

    @Column(name="BOARD_TITLE", length=100)
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
        }else if(boardTitleName.equals("")){
            throw new IllegalArgumentException("제목이 공백입니다.");
        }
    }

    @Override
    public String toString() {
        return boardTitleName;
    }
}
