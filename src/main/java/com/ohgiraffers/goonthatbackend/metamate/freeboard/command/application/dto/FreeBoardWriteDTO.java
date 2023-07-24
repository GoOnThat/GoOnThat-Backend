package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class FreeBoardWriteDTO {
    //이력 test
    private String boardCategory;   //카테고리
    private String boardTitle;  //제목
    private String boardContent; //내용
    private Long fileNo;

    public FreeBoardPost toEntity(MetaUser metaUser) {
        return FreeBoardPost.builder()
                .boardCategory(this.boardCategory)
                .boardTitle(this.boardTitle)
                .boardContent(this.boardContent)
                .metaUser(metaUser)
                .boardHits(0)
                .boardIsDeleted(false)
                .fileNo(this.fileNo)
                .build();

    }
}