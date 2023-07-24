package com.ohgiraffers.goonthatbackend.metamate.file.command.application.service;

import com.ohgiraffers.goonthatbackend.metamate.file.command.application.dto.FileDTO;
import com.ohgiraffers.goonthatbackend.metamate.file.command.domain.aggregate.entity.File;
import com.ohgiraffers.goonthatbackend.metamate.file.command.infra.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileService {

    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public Long saveFile(FileDTO fileDTO) {
        return fileRepository.save(fileDTO.toEntity()).getFileNo();
    }

    @Transactional
    public FileDTO getFile(Long fileNo) {
        File file = fileRepository.findById(fileNo).get();

        FileDTO fileDTO = FileDTO.builder()
                .fileNo(fileNo)
                .originFileName(file.getOriginFileName())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .metaUser(file.getMetaUser())
                .freeBoardPost(file.getFreeBoardPost())
                .build();

        return fileDTO;
    }

}
