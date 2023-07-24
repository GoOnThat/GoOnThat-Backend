package com.ohgiraffers.goonthatbackend.metamate.like.command.domain.repository;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity.Like;
//import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.vo.LikeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

//    @Modifying
//    @Query(value = "delete from like WHERE user_id = ?1 and board_id = ?2", nativeQuery = true)
//    int deleteByLike(long userId, long boardId);

    List<Like> findByFreeBoardPost(FreeBoardPost freeBoardPost);

//    List<Like> findByFreeBoardComment(FreeBoardComment freeBoardComment);

//    MetaUser findByBoardAndUser(FreeBoardPost freeBoardPost, MetaUser metaUser);
//
//    Like findByBoardAndUser(FreeBoardPost freeBoardPost, MetaUser metaUser);
}
