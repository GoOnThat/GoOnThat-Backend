package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class BoardWriter {

    @Column(name="BOARD_WRITER",nullable = false)
    private String boardWriterMemberName;

    protected BoardWriter() {

    }
    public BoardWriter(String boardWriterMemberName) {
        validateMemberNo(boardWriterMemberName);
        this.boardWriterMemberName = boardWriterMemberName;
    }

    private void validateMemberNo(String boardWriterMemberNo) {

        if(boardWriterMemberName == null) {
            throw new IllegalArgumentException("회원번호가 없습니다.");
        }else if(boardWriterMemberName.equals("")){
            throw new IllegalArgumentException("회원번호가 공백입니다.");
        }
    }

}
