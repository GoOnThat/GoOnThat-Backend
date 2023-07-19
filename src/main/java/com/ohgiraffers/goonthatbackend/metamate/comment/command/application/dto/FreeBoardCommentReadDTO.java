package com.ohgiraffers.goonthatbackend.metamate.comment.command.application.dto;

import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.FreeBoardComment;
import com.ohgiraffers.goonthatbackend.metamate.common.CalcCreateDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FreeBoardCommentReadDTO {

    private Long commentNo;
    private Long writerUserId;
    private String commentContent;
    private String commentWriter;
    private String createdDate;


    public FreeBoardCommentReadDTO fromEntity(FreeBoardComment freeBoardComment) {

        CalcCreateDate calc = new CalcCreateDate();

        return new FreeBoardCommentReadDTO(
                freeBoardComment.getMetauser().getId(),
                freeBoardComment.getCommentNo(),
                freeBoardComment.getCommentContent(),
                freeBoardComment.getMetauser().getNickname(),
                calc.calcCreateDate(freeBoardComment.getCreatedAt()));
    }

}