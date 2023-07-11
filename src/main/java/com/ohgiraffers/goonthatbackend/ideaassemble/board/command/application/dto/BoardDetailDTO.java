package com.ohgiraffers.goonthatbackend.ideaassemble.board.command.application.dto;

import java.util.Date;

public class BoardDetailDTO {

    private String boardCategory;   //카테고리
    private java.util.Date boardCreateDate; //글 작성일
    private String boardWriter; //글 작성자
    private String boardTitle;  //제목
    private String boardContent;    //내용

    public BoardDetailDTO() {}

    public BoardDetailDTO(String boardCategory, Date boardCreateDate, String boardWriter, String boardTitle, String boardContent) {
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

    public Date getBoardCreateDate() {
        return boardCreateDate;
    }

    public void setBoardCreateDate(Date boardCreateDate) {
        this.boardCreateDate = boardCreateDate;
    }

    public String getBoardWriter() {
        return boardWriter;
    }

    public void setBoardWriter(String boardWriter) {
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
                ", boardWriter='" + boardWriter + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                '}';
    }
}
