package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard,Long> {


}
