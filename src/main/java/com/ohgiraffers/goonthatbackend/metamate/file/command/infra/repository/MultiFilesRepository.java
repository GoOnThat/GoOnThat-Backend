package com.ohgiraffers.goonthatbackend.metamate.file.command.infra.repository;

import com.ohgiraffers.goonthatbackend.metamate.file.command.domain.aggregate.entity.MultiFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultiFilesRepository extends JpaRepository<MultiFiles, Long> {
    List<MultiFiles> findByFreeBoardPost_BoardNo(Long boardNo);
}
