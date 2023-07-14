package com.ohgiraffers.goonthatbackend.metamate.board.command.domain.service.mapper;

import com.ohgiraffers.goonthatbackend.metamate.board.command.application.dto.BoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.board.command.application.dto.BoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.board.command.application.dto.BoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.entity.FreeBoard;
import com.ohgiraffers.goonthatbackend.metamate.board.command.domain.aggregate.vo.*;

import java.time.LocalDate;

public class BoardMapper {

    /* BoardListDTO */
    public BoardListDTO boardListDTO(FreeBoard board) {

        /* 엔티티에서 꺼냄 */
        Long boardNo = board.getBoardNo(); //번호
        String boardCategory = board.getBoardCategory().getBoardCategoryNo(); //카테고리
        String boardTitle = board.getBoardTitle().getBoardTitleName(); //제목
        BoardDate boardDate = board.getBoardDate(); //글 작성일
        LocalDate boardCreateDate = board.getBoardDate().getBoardCreatedDate(); //글 작성일
        Integer boardWriter = board.getBoardWriter().getBoardWriterMemberNo(); //글 작성자
        Integer boardHits = board.getBoardHits().getBoardHits(); //글 조회수
        String boardDeleteYn = board.getBoardDeleteYn().getBoardDeleteYn(); //글 삭제여부

        /* 엔티티에서 꺼낸 값을 DTO로 옮김 */
        BoardListDTO boardListDTO = new BoardListDTO();
        boardListDTO.setBoardNo(boardNo);
        boardListDTO.setBoardCategory(boardCategory);
        boardListDTO.setBoardTitle(boardTitle);
        boardListDTO.setBoardCreateDate(boardDate.getBoardCreatedDate());
        boardListDTO.setBoardCreateDate(boardCreateDate);
        boardListDTO.setBoardWriter(boardWriter);
        boardListDTO.setBoardHits(boardHits);
        boardListDTO.setBoardDeleteYn(boardDeleteYn);

        /* 옮겨담은 DTO를 반환 */
        return boardListDTO;
    }

    /* BoardDetailDTO */
    public BoardDetailDTO boardDetailDTO(FreeBoard board) {

        /* 엔티티에서 꺼냄 */
        String boardCategory = board.getBoardCategory().getBoardCategoryNo(); //카테고리
        BoardDate boardDate = board.getBoardDate(); //글 작성일
        LocalDate boardCreateDate = board.getBoardDate().getBoardCreatedDate(); //글 작성일
        Integer boardWriter = board.getBoardWriter().getBoardWriterMemberNo(); //글 작성자
        String boardTitle = board.getBoardTitle().getBoardTitleName(); //제목
        String boardContent = board.getBoardContent().getBoardContentText(); //내용

        /* 엔티티에서 꺼낸 값을 DTO로 옮김 */
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardCategory(boardCategory);
        boardDetailDTO.setBoardCreateDate(boardDate.getBoardCreatedDate());
        boardDetailDTO.setBoardCreateDate(boardCreateDate);
        boardDetailDTO.setBoardWriter(boardWriter);
        boardDetailDTO.setBoardTitle(boardTitle);
        boardDetailDTO.setBoardContent(boardContent);

        /* 옮겨담은 DTO를 반환 */
        return boardDetailDTO;
    }

    /* BoardWriterDTO */
    public BoardWriteDTO boardWriteDTO(FreeBoard board) {

        /* 엔티티에서 꺼냄 */
        String boardCategory = board.getBoardCategory().getBoardCategoryNo(); //카테고리
        Integer boardWriter = board.getBoardWriter().getBoardWriterMemberNo(); //글 작성자
        String boardTitle = board.getBoardTitle().getBoardTitleName(); //제목
        String boardContent = board.getBoardContent().getBoardContentText(); //내용

        /* 엔티티에서 꺼낸 값을 DTO로 옮김 */
        BoardWriteDTO boardWriteDTO = new BoardWriteDTO();
        boardWriteDTO.setBoardCategory(boardCategory);
        boardWriteDTO.setBoardWriter(boardWriter);
        boardWriteDTO.setBoardTitle(boardTitle);
        boardWriteDTO.setBoardContent(boardContent);

        /* 옮겨담은 DTO를 반환 */
        return boardWriteDTO;
    }
}