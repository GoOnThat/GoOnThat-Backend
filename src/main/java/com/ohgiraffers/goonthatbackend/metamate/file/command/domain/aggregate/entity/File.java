package com.ohgiraffers.goonthatbackend.metamate.file.command.domain.aggregate.entity;

import com.ohgiraffers.goonthatbackend.metamate.domain.AuditingFields;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class File extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long fileNo;

    @Column(nullable = false)
    private String originFileName;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private MetaUser metaUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private FreeBoardPost freeBoardPost;

    @Builder
    public File(Long fileNo, String originFileName, String filename,
                String filePath, MetaUser metaUser, FreeBoardPost freeBoardPost) {
        this.fileNo = fileNo;
        this.originFileName = originFileName;
        this.filename = filename;
        this.filePath = filePath;
        this.metaUser = metaUser;
        this.freeBoardPost = freeBoardPost;
    }
}
