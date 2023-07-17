package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoard;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FreeBoardDetailDTO {

    private Long boardNo;   //보드 번호
    private String boardCategory;   //카테고리
    private LocalDate boardCreateDate; //글 작성일
    private String boardWriter; //글 작성자
    private String boardTitle;  //제목
    private String boardContent;    //내용
    public static FreeBoardDetailDTO entityToDTO(FreeBoard freeBoard){
        FreeBoardDetailDTO boardDetailDTO=new FreeBoardDetailDTO();
        boardDetailDTO.setBoardNo(freeBoard.getBoardNo());
        boardDetailDTO.setBoardCategory(freeBoard.getBoardCategory().getBoardCategoryNo());
        boardDetailDTO.setBoardCreateDate(freeBoard.getBoardDate().getBoardCreatedDate());
        boardDetailDTO.setBoardWriter(String.valueOf(freeBoard.getBoardWriter()));
        boardDetailDTO.setBoardTitle(String.valueOf(freeBoard.getBoardTitle().getBoardTitleName()));
        boardDetailDTO.setBoardContent(String.valueOf(freeBoard.getBoardContent().getBoardContentText()));
        return boardDetailDTO;
    }

    @Override
    public String toString() {
        return "FreeBoardDetailDTO{" +
                "boardNo=" + boardNo +
                ", boardCategory='" + boardCategory + '\'' +
                ", boardCreateDate=" + boardCreateDate +
                ", boardWriter='" + boardWriter + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                '}';
    }
}