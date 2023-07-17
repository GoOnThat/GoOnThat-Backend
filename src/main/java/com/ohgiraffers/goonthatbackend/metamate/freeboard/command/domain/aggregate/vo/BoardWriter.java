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

    public BoardWriter(String boardMetaUser) {
        validateMemberName(boardMetaUser);
        this.boardMetaUser = boardMetaUser;
    }

    private void validateMemberName(String boardMetaUser) {

        if (boardMetaUser == null) {
            throw new IllegalArgumentException("회원정보가 없습니다.");
        } else if (boardMetaUser.equals("")) {
            throw new IllegalArgumentException("회원정보가 공백입니다.");
        }
    }

}