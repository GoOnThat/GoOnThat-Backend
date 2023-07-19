package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FreeBoardWriteDTO {

    private String boardCategory;   //카테고리
    private String boardTitle;  //제목
    private String boardContent; //내용
    private MetaUser metaUser;
    private Integer boardHits; //글 조회수
    private Boolean boardIsDeleted; //삭제여부

    public FreeBoardPost toEntity(MetaUser metaUser) {
        return FreeBoardPost.builder()
                .boardCategory(this.boardCategory)
                .boardTitle(this.boardTitle)
                .boardContent(this.boardContent)
                .metaUser(this.metaUser)
                .boardHits(0)
                .boardIsDeleted(false)
                .build();

    }
}