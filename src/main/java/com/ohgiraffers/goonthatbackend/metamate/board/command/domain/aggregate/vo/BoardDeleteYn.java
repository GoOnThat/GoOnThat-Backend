package com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class BoardDeleteYn {

    @Column(name="BOARD_DELETE_YN",nullable = false)
    private String boardDeleteYn;

    public BoardDeleteYn() {
    }

    public BoardDeleteYn(String boardDeleteYn){

        validateBoardDeleteYn(boardDeleteYn);
        this.boardDeleteYn=boardDeleteYn;
    }

    private void validateBoardDeleteYn(String boardDeleteYn) {
        if(!(boardDeleteYn.toUpperCase().equals("Y")||boardDeleteYn.toUpperCase().equals("N"))){
            throw new IllegalArgumentException("잘못된 값 입니다.");
        }
    }
}
