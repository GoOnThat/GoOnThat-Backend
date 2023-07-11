package com.ohgiraffers.goonthatbackend.metamate.command.domain.aggregate.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

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

    @Column(name="BOARD_WRITER",length =100,nullable = false)
    private String boardWriter;

    @Column(name="BOARD_HITS",nullable = false)
    private int boardHits;

    @Column(name="BOARD_DELETE_YN",nullable = false)
    private String boardDeleteYn;

    @Column(name="BOARD_CREATED_DATE",nullable = false)
    private Date boardCreatedDate;

    @Column(name="BOARD_MODIFIED_DATE",nullable = false)
    private Date boardModifiedDate;

    @Builder
    public Board(String boardTitle, String boardContent, String boardWriter) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
    }
}
