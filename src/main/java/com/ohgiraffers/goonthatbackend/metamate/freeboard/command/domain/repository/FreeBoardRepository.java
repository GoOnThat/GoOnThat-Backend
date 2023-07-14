package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard,Long> {

    List<FreeBoardListDTO> freeBoradList(FreeBoard freeBoard);
}
