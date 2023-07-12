package com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class BoardWriter {

    @Column(name="BOARD_WRITER",nullable = false)
    private Integer boardWriterMemberNo;

    protected BoardWriter() {

    }
    public BoardWriter(Integer boardWriterMemberNo) {
        validateMemberNo(boardWriterMemberNo);
        this.boardWriterMemberNo = boardWriterMemberNo;
    }

    private void validateMemberNo(Integer boardWriterMemberNo) {

        if(boardWriterMemberNo < 0) {
            throw new IllegalArgumentException("회원번호는 음수일 수 없습니다.");
        }
    }

}
