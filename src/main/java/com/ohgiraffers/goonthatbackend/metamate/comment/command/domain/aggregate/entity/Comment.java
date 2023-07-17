package com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.vo.*;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoard;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_NO")
    private Long commentNo;

    private CommentContent commentContent;

    private CommentWriter commentWriter;

    private CommentDate commentDate;

    private CommentDeleteYn commentDeleteYn;
//
//    @ElementCollection
//    private List<CommentChildren> children;

    private CommentParent parent;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "FREE_BOARD_NO")
    private FreeBoard freeBoard;

}