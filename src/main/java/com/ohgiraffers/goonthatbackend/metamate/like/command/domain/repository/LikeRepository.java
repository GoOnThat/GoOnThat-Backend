package com.ohgiraffers.goonthatbackend.metamate.like.command.domain.repository;


import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.like.command.domain.aggregate.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByFreeBoardPost(FreeBoardPost freeBoardPost);

//    @Modifying
//    @Query(value = "delete from to_like WHERE id = ?1 AND board_no = ?2",nativeQuery = true)
//    int deleteLike(long likeNo, FreeBoardPost freeBoardPost);

    Optional<Like> findByFreeBoardPostAndMetaUser(FreeBoardPost freeBoardPost, MetaUser metaUser);
}

