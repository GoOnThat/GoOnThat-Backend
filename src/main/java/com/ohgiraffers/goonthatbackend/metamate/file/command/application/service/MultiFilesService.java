package com.ohgiraffers.goonthatbackend.metamate.file.command.application.service;

import com.ohgiraffers.goonthatbackend.metamate.common.MD5Generator;
import com.ohgiraffers.goonthatbackend.metamate.file.command.application.dto.MultiFilesReadDTO;
import com.ohgiraffers.goonthatbackend.metamate.file.command.application.dto.MultiFilesWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.file.command.domain.aggregate.entity.MultiFiles;
import com.ohgiraffers.goonthatbackend.metamate.file.command.infra.repository.MultiFilesRepository;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class MultiFilesService {

    private MultiFilesRepository multiFilesRepository;


    @Transactional
    public void saveFile(MultipartFile file, FreeBoardWriteDTO boardDTO) throws IOException, NoSuchAlgorithmException {

        String originFileName = file.getOriginalFilename();
        if (originFileName == null) {
            // 파일 이름이 null인 경우에 대한 예외 처리
            throw new IllegalArgumentException("파일 이름이 null입니다.");
        }

        String fileName = new MD5Generator(originFileName).toString();
        String savePath = System.getProperty("user.home") + File.separator + "files";
        File directory = new File(savePath);
        //디렉토리 없을경우 오류처리
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("디렉토리가 성공적으로 생성되었습니다.");
            } else {
                System.out.println("디렉토리 생성에 실패했습니다.");
                throw new IllegalArgumentException("파일 디렉토리 생성에 실패했습니다.");
            }
        }

        String filePath = savePath + File.separator + fileName;
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            // 파일 전송 과정에서 발생한 예외 처리
            throw new IllegalArgumentException("파일 전송 중 오류가 발생했습니다.");
        }
        MultiFilesWriteDTO multiFilesWriteDTO = new MultiFilesWriteDTO();
        multiFilesWriteDTO.setOriginFileName(originFileName);
        multiFilesWriteDTO.setFileName(fileName);
        multiFilesWriteDTO.setFilePath(filePath);
//        multiFilesWriteDTO.setFreeBoardPost(boardDTO.);

        try {
            multiFilesRepository.save(multiFilesWriteDTO.toEntity());
        } catch (RuntimeException e) {
            // 데이터베이스 저장 과정에서 발생한 예외 처리
            throw new IllegalArgumentException("데이터베이스 저장 중 오류가 발생했습니다.", e);
        }
    }

    @Transactional
    public MultiFilesReadDTO getFile(Long fileNo) {
        MultiFiles file = multiFilesRepository.findById(fileNo).get();

        MultiFilesReadDTO fileDTO = MultiFilesReadDTO.builder()
                .fileNo(fileNo)
                .originFileName(file.getOriginFileName())
                .fileName(file.getFileName())
                .filePath(file.getFilePath())
                .metaUser(file.getMetaUser())
                .freeBoardPost(file.getFreeBoardPost())
                .build();

        return fileDTO;
    }

}
