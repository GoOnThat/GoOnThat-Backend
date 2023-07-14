package com.ohgiraffers.goonthatbackend.metamate.board.command.application.dto;

import java.time.LocalDate;
import java.util.Date;

public class BoardDetailDTO {

    private String boardCategory;   //카테고리
    private LocalDate boardCreateDate; //글 작성일
    private Integer boardWriter; //글 작성자
    private String boardTitle;  //제목
    private String boardContent;    //내용

    public BoardDetailDTO() {}

    public BoardDetailDTO(String boardCategory, LocalDate boardCreateDate, Integer boardWriter, String boardTitle, String boardContent) {
        this.boardCategory = boardCategory;
        this.boardCreateDate = boardCreateDate;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }

    public String getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(String boardCategory) {
        this.boardCategory = boardCategory;
    }

    public LocalDate getBoardCreateDate() {
        return boardCreateDate;
    }

    public void setBoardCreateDate(LocalDate boardCreateDate) {
        this.boardCreateDate = boardCreateDate;
    }

    public Integer getBoardWriter() {
        return boardWriter;
    }

    public void setBoardWriter(Integer boardWriter) {
        this.boardWriter = boardWriter;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    @Override
    public String toString() {
        return "BoardDetailDTO{" +
                "boardCategory='" + boardCategory + '\'' +
                ", boardCreateDate=" + boardCreateDate +
                ", boardWriter=" + boardWriter +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                '}';
    }
}