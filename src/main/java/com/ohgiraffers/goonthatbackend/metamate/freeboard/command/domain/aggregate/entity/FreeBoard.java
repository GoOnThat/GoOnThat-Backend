package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity;


import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.Comment;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.vo.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FREE_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FREE_BOARD_NO")
    private Long boardNo;

    private BoardCategory boardCategory;

    private BoardTitle boardTitle;

    private BoardContent boardContent;

    private BoardWriter boardWriter;

    private BoardHits boardHits;

    private BoardDeleteYn boardDeleteYn;

    private BoardDate boardDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

}