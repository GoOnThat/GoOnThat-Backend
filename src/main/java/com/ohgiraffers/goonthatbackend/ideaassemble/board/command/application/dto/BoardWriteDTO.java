package com.ohgiraffers.goonthatbackend.ideaassemble.board.command.application.dto;

public class BoardWriteDTO {

    private String boardCategory;   //카테고리
    private String boardWriter; //글 작성자
    private String boardTitle;  //제목
    private String boardContent;    //내용

    public BoardWriteDTO() {}

    public BoardWriteDTO(String boardCategory, String boardTitle, String boardContent) {
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }

    public BoardWriteDTO(String boardCategory, String boardWriter, String boardTitle, String boardContent) {
        this.boardCategory = boardCategory;
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
        return "BoardWriteDTO{" +
                "boardCategory='" + boardCategory + '\'' +
                ", boardWriter='" + boardWriter + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                '}';
    }
}
