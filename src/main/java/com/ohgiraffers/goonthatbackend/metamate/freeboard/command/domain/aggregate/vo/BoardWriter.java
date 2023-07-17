package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class BoardWriter {

    @Column(name="BOARD_WRITER")
    private String boardWriterMemberName;

    protected BoardWriter() {

    }
    public BoardWriter(String boardWriterMemberName) {
        validateMemberName(boardWriterMemberName);
        this.boardWriterMemberName = boardWriterMemberName;
    }

    private void validateMemberName(String boardWriterMemberName) {

        if(boardWriterMemberName == null) {
            throw new IllegalArgumentException("회원번호가 없습니다.");
        }else if(boardWriterMemberName.equals("")){
            throw new IllegalArgumentException("회원번호가 공백입니다.");
        }
    }

}

