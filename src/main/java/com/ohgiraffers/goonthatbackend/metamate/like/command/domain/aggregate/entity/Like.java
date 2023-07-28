package com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.domain.AuditingFields;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "to_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Like extends AuditingFields{

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private MetaUser metaUser;

    @Builder
    public Like(Long likeNo, FreeBoardPost freeBoardPost, FreeBoardComment freeBoardComment, MetaUser metaUser) {
        this.likeNo = likeNo;
        this.freeBoardPost = freeBoardPost;
        this.freeBoardComment = freeBoardComment;
        this.metaUser = metaUser;

    }



    @OneToMany(mappedBy = "freeBoardPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();
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

    // 좋아요 수를 반환하는 메서드
//    public int getLikeCount() {
//        return likes.size();
//    }
}