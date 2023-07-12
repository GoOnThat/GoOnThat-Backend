package com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.entity;

import com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.vo.*;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "FREE_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOARD_NO")
    private Long boardNo;

    @Embedded
    private BoardCategory boardCategory;

    @Embedded
    private BoardTitle boardTitle;

    @Embedded
    private BoardContent boardContent;

    @Embedded
    private BoardWriter boardWriter;

    @Embedded
    private BoardHits boardHits;

    @Column(name="BOARD_DELETE_YN",nullable = false)
    private String boardDeleteYn;

    @Embedded
    private BoardDate boardDate;


}
