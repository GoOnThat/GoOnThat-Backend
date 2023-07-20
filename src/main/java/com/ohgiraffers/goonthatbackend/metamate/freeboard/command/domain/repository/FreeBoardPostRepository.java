package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreeBoardPostRepository extends JpaRepository<FreeBoardPost,Long> {

    List<FreeBoardPost> findByBoardIsDeletedFalse(); //삭제여부 false만 조회



}
