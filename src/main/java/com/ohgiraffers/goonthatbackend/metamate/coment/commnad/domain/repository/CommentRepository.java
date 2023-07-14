package com.ohgiraffers.goonthatbackend.metamate.coment.commnad.domain.repository;


import com.ohgiraffers.goonthatbackend.metamate.coment.commnad.domain.aggregate.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {


}
