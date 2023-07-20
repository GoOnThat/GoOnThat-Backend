package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard,Long> {

}
