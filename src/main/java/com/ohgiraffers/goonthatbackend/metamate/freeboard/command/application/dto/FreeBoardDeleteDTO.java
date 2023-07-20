package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;


import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//이력 test
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class FreeBoardDeleteDTO {

    private String boardCategory;
    private String boardTitle;
    private String boardContent;

    public FreeBoardPost toEntity(){
        return FreeBoardPost.builder()
                .boardCategory(this.boardCategory)
                .boardTitle(this.boardTitle)
                .boardContent(this.boardContent)
                .build();
    }
}
