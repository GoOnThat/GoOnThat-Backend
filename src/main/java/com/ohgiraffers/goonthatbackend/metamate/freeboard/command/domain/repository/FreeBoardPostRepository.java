package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeBoardPostRepository extends JpaRepository<FreeBoardPost,Long> {
    FreeBoardPost findByBoardTitle(String boardTitle);
}
