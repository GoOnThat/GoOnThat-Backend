package com.ohgiraffers.goonthatbackend.metamate.board.command.domain.repository;
import com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<FreeBoard,Long> {


}
