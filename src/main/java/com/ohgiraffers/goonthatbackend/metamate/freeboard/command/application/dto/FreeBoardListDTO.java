package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.common.CalcCreateDate;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FreeBoardListDTO {

    private Long boardNo;   //번호
    private String createdAt; // 생성일시
    private String boardCategory;   //카테고리
    private String boardTitle;  //제목
    private String boardContent;    //내용
    private Integer boardHits;      //글 조회수


    public FreeBoardListDTO fromEntity(FreeBoardPost boardPost) {
        CalcCreateDate cal= new CalcCreateDate();
        return new FreeBoardListDTO(
                boardPost.getBoardNo()
                , cal.calcCreateDate(boardPost.getCreatedAt())
                , boardPost.getBoardCategory()
                , boardPost.getBoardTitle()
                , boardPost.getBoardContent()
                , boardPost.getBoardHits()
        );
    }
}

