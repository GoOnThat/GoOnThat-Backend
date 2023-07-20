package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.domain.AuditingFields;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FreeBoardPost extends AuditingFields {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long boardNo;

    private String boardCategory;

    private String boardTitle;

    @Column(columnDefinition = "TEXT")
    private String boardContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private MetaUser metaUser;

    private String boardWriter;

    private int boardHits;

    @OneToMany(mappedBy = "post")
    private List<FreeBoardComment> CommentList = new ArrayList<>();

    private boolean boardIsDeleted = false;

    @Builder
    public FreeBoardPost(String boardCategory, String boardTitle, String boardContent,
                         MetaUser metaUser, String boardWriter, int boardHits, List<FreeBoardComment> CommentList,
                         boolean boardIsDeleted) {
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.metaUser = metaUser;
        this.boardHits = boardHits;
        this.boardIsDeleted = boardIsDeleted;
    }


}

