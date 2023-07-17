package com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.repository;


import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {


}
