package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
public class FreeBoardWriteDTO {

    private String boardCategory;   //카테고리
    private String boardWriter; //글 작성자
    private String boardTitle;  //제목
    private String boardContent; //내용
    private Integer boardHits; //글 조회수
    private Boolean boardIsDeleted; //삭제여부

    public FreeBoardPost toEntity() {
        return FreeBoardPost.builder()
                .boardCategory(this.boardCategory)
                .boardWriter(this.boardWriter)
                .boardTitle(this.boardTitle)
                .boardContent(this.boardContent)
                .boardHits(0)
                .boardIsDeleted(false)
                .build();

    }
}