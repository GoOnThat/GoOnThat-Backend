package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.dto.FreeBoardCommentReadDTO;
import com.ohgiraffers.goonthatbackend.metamate.common.CalcCreateDate;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private boolean boardIsDeleted;// 삭제여부
    private List<FreeBoardCommentReadDTO> commentList;
    private String boardWriter;
    private MetaUser metaUser;


    public FreeBoardDetailDTO fromEntity(FreeBoardPost boardPost,List<FreeBoardCommentReadDTO> commentList) {
        CalcCreateDate cal= new CalcCreateDate();
        String boardWriter=boardPost.getMetaUser().getNickname();
        return new FreeBoardDetailDTO(
                boardPost.getBoardNo()
                , cal.calcCreateDate(boardPost.getCreatedAt())
                , boardPost.getBoardCategory()
                , boardPost.getBoardTitle()
                , boardPost.getBoardContent()
                , boardPost.getBoardHits()
                , boardPost.isBoardIsDeleted()
                , commentList
                , boardWriter
                , boardPost.getMetaUser()
                );
    }

}