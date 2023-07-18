package com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity;

import com.ohgiraffers.goonthatbackend.metamate.domain.AuditingFields;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FreeBoardComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_NO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FREE_BOARD_NO")
    private FreeBoardPost freeBoardPost;

    @Column
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private MetaUser metauser;

    private String commentWriter;

    @Column
    private String commentDeleteYn;

    @Builder
    public FreeBoardComment(FreeBoardPost freeBoardPost, String commentContent,
                            MetaUser metauser, String commentWriter, String commentDeleteYn) {
        this.freeBoardPost = freeBoardPost;
        this.commentContent = commentContent;
        this.metauser = metauser;
        this.commentWriter = commentWriter;
        this.commentDeleteYn = commentDeleteYn;
    }
}