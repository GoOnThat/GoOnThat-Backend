package com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.domain.AuditingFields;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.vo.LikeVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Like")
@Table(name = "LIKE")
@NoArgsConstructor
@Getter
public class Like implements Serializable {

//    @EmbeddedId
//    private LikeVO like;
//
//    @JoinColumn(name = "id")
//    private MetaUser metauser;
//
//    public Like(LikeVO like) {
//        this.like = like;
//    }
//
//    public Like(long userId, long boardId) {
//        this.like = new LikeVO(userId, boardId);
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long likeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private FreeBoardPost freeBoardPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_no")
    private FreeBoardComment freeBoardComment;

    @Column
    private boolean likeStatus;

    @Column
    private int liked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MetaUser metaUser;

    @Builder
    public Like(FreeBoardPost freeBoardPost, MetaUser metaUser) {
        this.likeNo = likeNo;
        this.freeBoardPost = freeBoardPost;
        this.freeBoardComment = freeBoardComment;
        this.metaUser = metaUser;
        this.likeStatus = true;
        this.liked = 0;
    }
//
//    public void unLikeBoard(FreeBoardPost freeBoardPost) {
//        this.likeStatus = false;
//        freeBoardPost.setLiked(freeBoardPost.getLiked() - 1);
//    }

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
