package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class FreeBoardUpdateDTO {

    private String boardCategory;   //카테고리
    private String boardTitle;  //제목
    private String boardContent; //내용
    public FreeBoardPost upToEntity(){
        return FreeBoardPost.builder()
                .boardCategory(this.boardCategory)
                .boardTitle(this.boardTitle)
                .boardContent(this.boardContent)
                .build();
    }
}
