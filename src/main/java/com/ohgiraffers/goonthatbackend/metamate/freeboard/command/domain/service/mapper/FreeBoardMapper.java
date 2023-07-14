package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.service.mapper;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoard;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.vo.*;

import java.time.LocalDate;

public class FreeBoardMapper {

    /* BoardListDTO */
    public FreeBoardListDTO boardListDTO(FreeBoard board) {

        /* 엔티티에서 꺼냄 */
        Long boardNo = board.getBoardNo(); //번호
        String boardCategory = board.getBoardCategory().getBoardCategoryNo(); //카테고리
        String boardTitle = board.getBoardTitle().getBoardTitleName(); //제목
        BoardDate boardDate = board.getBoardDate(); //글 작성일
        LocalDate boardCreateDate = board.getBoardDate().getBoardCreatedDate(); //글 작성일
        String boardWriter = board.getBoardWriter().getBoardWriterMemberName(); //글 작성자
        Integer boardHits = board.getBoardHits().getBoardHits(); //글 조회수
        String boardDeleteYn = board.getBoardDeleteYn().getBoardDeleteYn(); //글 삭제여부

        /* 엔티티에서 꺼낸 값을 DTO로 옮김 */
        FreeBoardListDTO boardListDTO = new FreeBoardListDTO();
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
    public FreeBoardDetailDTO boardDetailDTO(FreeBoard board) {

        /* 엔티티에서 꺼냄 */
        String boardCategory = board.getBoardCategory().getBoardCategoryNo(); //카테고리
        BoardDate boardDate = board.getBoardDate(); //글 작성일
        LocalDate boardCreateDate = board.getBoardDate().getBoardCreatedDate(); //글 작성일
        String boardWriter = board.getBoardWriter().getBoardWriterMemberName(); //글 작성자
        String boardTitle = board.getBoardTitle().getBoardTitleName(); //제목
        String boardContent = board.getBoardContent().getBoardContentText(); //내용

        /* 엔티티에서 꺼낸 값을 DTO로 옮김 */
        FreeBoardDetailDTO boardDetailDTO = new FreeBoardDetailDTO();
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
    public FreeBoardWriteDTO boardWriteDTO(FreeBoard board) {

        /* 엔티티에서 꺼냄 */
        String boardCategory = board.getBoardCategory().getBoardCategoryNo(); //카테고리
        String boardWriter = board.getBoardWriter().getBoardWriterMemberName(); //글 작성자
        String boardTitle = board.getBoardTitle().getBoardTitleName(); //제목
        String boardContent = board.getBoardContent().getBoardContentText(); //내용

        /* 엔티티에서 꺼낸 값을 DTO로 옮김 */
        FreeBoardWriteDTO boardWriteDTO = new FreeBoardWriteDTO();
        boardWriteDTO.setBoardCategory(boardCategory);
        boardWriteDTO.setBoardWriter(boardWriter);
        boardWriteDTO.setBoardTitle(boardTitle);
        boardWriteDTO.setBoardContent(boardContent);

        /* 옮겨담은 DTO를 반환 */
        return boardWriteDTO;
    }
}