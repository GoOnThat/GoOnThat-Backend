package com.ohgiraffers.goonthatbackend.metamate.file.command.infra.repository;

import com.ohgiraffers.goonthatbackend.metamate.file.command.domain.aggregate.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository  extends JpaRepository<File, Long> {
}
