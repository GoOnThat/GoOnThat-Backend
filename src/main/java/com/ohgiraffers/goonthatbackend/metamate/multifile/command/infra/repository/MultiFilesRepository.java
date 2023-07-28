package com.ohgiraffers.goonthatbackend.metamate.multifile.command.infra.repository;

import com.ohgiraffers.goonthatbackend.metamate.multifile.command.domain.aggregate.entity.MultiFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultiFilesRepository extends JpaRepository<MultiFiles, Long> {
    //파일조회
    List<MultiFiles> findByFreeBoardPost_BoardNo(Long boardNo);

    //보드번호가 같은 파일조회

}
