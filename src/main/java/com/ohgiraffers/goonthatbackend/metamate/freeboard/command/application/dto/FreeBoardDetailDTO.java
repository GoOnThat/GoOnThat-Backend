package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.common.CalcCreateDate;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FreeBoardDetailDTO {

    private Long boardNo; //글 번호
    private String createdAt; // 생성일시
    private String boardCategory;//카테고리
    private String boardTitle;  //제목
    private String boardContent; //내용
    private int boardHits; //조회수
    private List<FreeBoardComment> commentList = new ArrayList<>();
    private String boardWriter;

    public FreeBoardDetailDTO fromEntity(FreeBoardPost boardPost,List<FreeBoardComment> commentList) {
        CalcCreateDate cal= new CalcCreateDate();
        String boardWriter=boardPost.getMetaUser().getNickname();
        return new FreeBoardDetailDTO(
                boardPost.getBoardNo()
                , cal.calcCreateDate(boardPost.getCreatedAt())
                , boardPost.getBoardCategory()
                , boardPost.getBoardTitle()
                , boardPost.getBoardContent()
                , boardPost.getBoardHits()
                , commentList
                , boardWriter
                );
    }

}