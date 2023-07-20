package com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity;

import com.ohgiraffers.goonthatbackend.metamate.domain.AuditingFields;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FreeBoardComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long commentNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private FreeBoardPost freeBoardPost;

    @Column
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private MetaUser metauser;

    @Builder
    public FreeBoardComment(FreeBoardPost freeBoardPost, String commentContent,
                            MetaUser metauser) {
        this.freeBoardPost = freeBoardPost;
        this.commentContent = commentContent;
        this.metauser = metauser;
    }
}