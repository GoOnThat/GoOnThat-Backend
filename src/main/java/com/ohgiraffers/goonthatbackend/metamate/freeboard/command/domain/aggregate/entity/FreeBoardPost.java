package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.domain.AuditingFields;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "freeboard")
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

    private int boardHits;

    @OneToMany(mappedBy = "freeBoardPost", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("commentNo asc")
    private List<FreeBoardComment> commentList = new ArrayList<>();

    private boolean boardIsDeleted;

    @Builder
    public FreeBoardPost(String boardCategory, String boardTitle, String boardContent,
                         MetaUser metaUser, int boardHits, List<FreeBoardComment> commentList,
                         boolean boardIsDeleted) {
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.metaUser = metaUser;
        this.boardHits = boardHits;
        this.commentList = commentList;
        this.boardIsDeleted = boardIsDeleted;
    }


    public void update(String newCategory, String newTitle, String newContent) {
        this.boardCategory = newCategory;
        this.boardTitle = newTitle;
        this.boardContent = newContent;
    }

    public void delete() {
        this.boardIsDeleted = true;
    }
}

