package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FreeBoardWriteDTO {

    private String boardCategory;   //카테고리
    private String boardWriter; //글 작성자
    private String boardTitle;  //제목
    private String boardContent;    //내용
    private Integer boardHits; //글 조회수

    public FreeBoardWriteDTO toEntity(FreeBoardPost boardPost) {
        return new FreeBoardWriteDTO(
                boardPost.getBoardCategory()
                , boardPost.getBoardWriter()
                , boardPost.getBoardTitle()
                , boardPost.getBoardContent()
                , boardPost.getBoardHits()
        );
    }
}