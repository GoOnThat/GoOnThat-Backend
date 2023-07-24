package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FreeBoardPostRepository extends JpaRepository<FreeBoardPost,Long> {

    Page<FreeBoardPost> findByBoardIsDeletedFalse(Pageable pageable); //삭제여부 false만 조회

//    Page<FreeBoardPost> findByBoardContaining(String searchKeyword, Pageable pageable);

}
