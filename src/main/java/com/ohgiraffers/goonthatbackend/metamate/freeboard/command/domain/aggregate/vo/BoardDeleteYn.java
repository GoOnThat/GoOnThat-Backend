package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class BoardDeleteYn {

    @Column(name="BOARD_DELETE_YN")
    private String boardDeleteYn;

    public BoardDeleteYn() {
    }

    public BoardDeleteYn(String boardDeleteYn){
        validateBoardDeleteYn(boardDeleteYn);
        this.boardDeleteYn=boardDeleteYn;
    }

    private void validateBoardDeleteYn(String boardDeleteYn) {
        if(boardDeleteYn==null){
            throw new IllegalArgumentException("값을 입력해 주세요");
        }else if(boardDeleteYn.equals("")){
            throw new IllegalArgumentException("공백을 입력할수 없습니다.");
        }else if(!(boardDeleteYn.toUpperCase().equals("Y")||boardDeleteYn.toUpperCase().equals("N"))){
            throw new IllegalArgumentException("잘못된 값 입니다.");
        }
    }
}
