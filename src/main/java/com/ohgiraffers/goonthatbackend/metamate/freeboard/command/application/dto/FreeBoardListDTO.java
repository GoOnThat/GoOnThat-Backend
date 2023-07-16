package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class FreeBoardListDTO {

    private Long boardNo;   //번호
    private String boardCategory;   //카테고리
    private String boardTitle;  //제목
    private String boardWriter;    //글 작성자
    private Integer boardHits;      //글 조회수
    private String boardDeleteYn;    //글 삭제여부

}

