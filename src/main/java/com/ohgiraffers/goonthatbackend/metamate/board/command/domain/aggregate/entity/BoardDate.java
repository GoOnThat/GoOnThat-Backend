package com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import java.time.LocalDate;
import java.util.Date;

@Embeddable
public class BoardDate {

    @Column(name="BOARD_CREATED_DATE",nullable = false)
    private LocalDate boardCreatedDate;

    @Column(name="BOARD_MODIFIED_DATE",nullable = false)
    private LocalDate boardModifiedDate;

    @Column
    private int period;

    protected BoardDate() {
    }

    public BoardDate(LocalDate boardCreatedDate, LocalDate boardModifiedDate) {
        validateIsPast(boardCreatedDate);
        this.boardCreatedDate = boardCreatedDate;
        this.boardModifiedDate = boardModifiedDate;
        this.period = getPeriod(boardCreatedDate, boardModifiedDate);
    }

    private int getPeriod(LocalDate boardCreatedDate, LocalDate boardModifiedDate) {

        return 1;
    }

    private void validateIsPast(LocalDate boardCreatedDate) {

        if(boardCreatedDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("작성일이 미래일 수 없습니다.");
        }
    }

    public int getPeriod() {

        return this.period;
    }
}
