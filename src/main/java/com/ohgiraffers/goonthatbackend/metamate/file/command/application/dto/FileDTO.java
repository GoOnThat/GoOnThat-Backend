package com.ohgiraffers.goonthatbackend.metamate.file.command.application.dto;


import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.file.command.domain.aggregate.entity.File;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDTO {

    private Long fileNo;

    private String originFileName;

    private String filename;

    private String filePath;

    private MetaUser metaUser;

    private FreeBoardPost freeBoardPost;

    public File toEntity() {
        File build = File.builder()
                .fileNo(fileNo)
                .originFileName(originFileName)
                .filename(filename)
                .filePath(filePath)
                .metaUser(metaUser)
                .freeBoardPost(freeBoardPost)
                .build();

        return build;
    }

    @Builder
    public FileDTO(Long fileNo, String originFileName, String filename, String filePath, MetaUser metaUser, FreeBoardPost freeBoardPost) {
        this.fileNo = fileNo;
        this.originFileName = originFileName;
        this.filename = filename;
        this.filePath = filePath;
        this.metaUser = metaUser;
        this.freeBoardPost = freeBoardPost;
    }
}
