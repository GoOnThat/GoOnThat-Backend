package com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BoardCategory {

    @Column(name="BOARD_CATEGORY", length=20, nullable = false)
    private String boardCategoryNo;

    protected BoardCategory(){

    }

    public BoardCategory(String boardCategoryNo){
        validateBoardCategoryNo(boardCategoryNo);
        this.boardCategoryNo=boardCategoryNo;
    }

    private void validateBoardCategoryNo(String boardCategoryNo) {

        if(boardCategoryNo==null){
            throw new IllegalArgumentException("카테고리를 지정해 주세요");
        }
    }


}
