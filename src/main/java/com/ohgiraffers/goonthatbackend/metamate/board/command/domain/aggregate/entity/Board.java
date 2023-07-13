package com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.entity;

import com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.vo.*;
import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name="freeboard")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOARD_NO")
    private Long boardNo;

    private BoardCategory boardCategory;

    private BoardTitle boardTitle;

    private BoardContent boardContent;

    private BoardWriter boardWriter;

    private BoardHits boardHits;

    private BoardDeleteYn boardDeleteYn;

    private BoardDate boardDate;

}
