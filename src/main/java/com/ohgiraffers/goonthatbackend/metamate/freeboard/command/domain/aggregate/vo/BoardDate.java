package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
@EqualsAndHashCode
public class BoardDate {

    @CreatedDate
    @Column(name = "FREE_BOARD_CREATED_DATE", nullable = false)
    private LocalDate boardCreatedDate;

    @LastModifiedDate
    @Column(name = "FREE_BOARD_MODIFIED_DATE")
    private LocalDate boardModifiedDate;


    protected BoardDate() {
    }

    //등록
    public BoardDate(LocalDate boardCreatedDate, LocalDate boardModifiedDate) {
        validateIsPast(boardCreatedDate);
        validateIsPast(boardModifiedDate);
        this.boardCreatedDate = boardCreatedDate;
        this.boardModifiedDate = boardModifiedDate;
    }
    //수정
    public BoardDate(LocalDate boardModifiedDate) {
        validateIsPast(boardModifiedDate);
        this.boardModifiedDate = boardModifiedDate;
    }

    private void validateIsPast(LocalDate date) {

        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("작성이 미래일 수 없습니다.");
        }
    }

}