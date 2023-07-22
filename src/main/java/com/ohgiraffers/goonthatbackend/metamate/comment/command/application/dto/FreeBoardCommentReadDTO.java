package com.ohgiraffers.goonthatbackend.metamate.comment.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.common.CalcCreateDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FreeBoardCommentReadDTO {

    private Long commentNo;
    private String commentContent;
    private String commentWriter;
    private String createdDate;


    public FreeBoardCommentReadDTO fromEntity(FreeBoardComment freeBoardComment) {

        CalcCreateDate calc = new CalcCreateDate();

        return new FreeBoardCommentReadDTO(
                freeBoardComment.getCommentNo(),
                freeBoardComment.getCommentContent(),
                freeBoardComment.getMetauser().getNickname(),
                calc.calcCreateDate(freeBoardComment.getCreatedAt()));
    }

}