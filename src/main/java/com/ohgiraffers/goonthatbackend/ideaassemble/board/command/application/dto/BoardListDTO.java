package com.ohgiraffers.goonthatbackend.ideaassemble.board.command.application.dto;

import java.util.Date;

public class BoardListDTO {

    private long boardNo;   //번호
    private String boardCategory;   //카테고리
    private String boardTitle;  //제목
    private java.util.Date boardCreateDate; //글 작성일
    private String boardWriter;    //글 작성자
    private int boardHits;      //글 조회수
    private String boardDeleteYn;    //글 삭제야부

    public BoardListDTO() {}

    public BoardListDTO(long boardNo, String boardCategory, String boardTitle, Date boardCreateDate, String boardWriter, int boardHits, String boardDeleteYn) {
        this.boardNo = boardNo;
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardCreateDate = boardCreateDate;
        this.boardWriter = boardWriter;
        this.boardHits = boardHits;
        this.boardDeleteYn = boardDeleteYn;
    }

    public long getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(long boardNo) {
        this.boardNo = boardNo;
    }

    public String getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(String boardCategory) {
        this.boardCategory = boardCategory;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
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

    public int getBoardHits() {
        return boardHits;
    }

    public void setBoardHits(int boardHits) {
        this.boardHits = boardHits;
    }

    public String getBoardDeleteYn() {
        return boardDeleteYn;
    }

    public void setBoardDeleteYn(String boardDeleteYn) {
        this.boardDeleteYn = boardDeleteYn;
    }

    @Override
    public String toString() {
        return "BoardListDTO{" +
                "boardNo=" + boardNo +
                ", boardCategory='" + boardCategory + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardCreateDate=" + boardCreateDate +
                ", boardWriter='" + boardWriter + '\'' +
                ", boardHits=" + boardHits +
                ", boardDeleteYn='" + boardDeleteYn + '\'' +
                '}';
    }
}
