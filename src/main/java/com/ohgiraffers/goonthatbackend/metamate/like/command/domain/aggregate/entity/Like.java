package com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity;
import com.ohgiraffers.goonthatbackend.metamate.domain.AuditingFields;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Table(name = "to_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long likeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private FreeBoardPost freeBoardPost;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "comment_no")
//    private FreeBoardComment freeBoardComment;

//    @Column
//    private boolean likeStatus;
//
//    @Column
//    private int liked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private MetaUser metaUser;

//    @Column
//    private String likeMan;

    @Builder
    public Like(Long likeNo, FreeBoardPost freeBoardPost, MetaUser metaUser,
                boolean likeStatus, int liked, String likeMan) {
        this.likeNo = likeNo;
        this.freeBoardPost = freeBoardPost;
        this.metaUser = metaUser;
//        this.likeStatus = likeStatus;
//        this.liked = liked;
//        this.likeMan = likeMan;
    }

//    public void unLikeBoard(FreeBoardPost freeBoardPost) {
//        this.likeStatus = false;
//        freeBoardPost.setLiked(freeBoardPost.getLiked() - 1);
//    }
//
//    public void increaseLikeCount() {
//        this.liked += 1;
//    }
//
//    public void decreaseLikeCount() {
//        this.liked -= 1;
//    }
//
//    public void setLiked() {
//    }
}
