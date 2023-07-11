package com.ohgiraffers.goonthatbackend.metamate.command.domain.repository;
import com.ohgiraffers.goonthatbackend.metamate.command.domain.aggregate.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Integer> {


}
