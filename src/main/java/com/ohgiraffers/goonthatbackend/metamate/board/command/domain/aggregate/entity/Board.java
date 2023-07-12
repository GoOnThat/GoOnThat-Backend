package com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOARD_ID")
    private int boardId;

    @Column(name="BOARD_CATEGORY", length=20, nullable = false)
    private String boardCategory;

    @Column(name="BOARD_TITLE", length=100, nullable = false)
    private String boardTitle;

    @Column(name="BOARD_CONTENT", columnDefinition = "TEXT",nullable = false)
    private String boardContent;

    @Embedded
    private BoardWriter boardWriter;

    @Column(name="BOARD_HITS",nullable = false)
    private int boardHits;

    @Column(name="BOARD_DELETE_YN",nullable = false)
    private String boardDeleteYn;

    @Embedded
    private BoardDate boardDate;



    @Builder
    public Board(String boardTitle, String boardContent, BoardWriter boardWriter) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
    }
}
