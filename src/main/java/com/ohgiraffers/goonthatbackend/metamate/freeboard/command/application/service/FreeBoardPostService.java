package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import java.util.List;

public interface FreeBoardPostService {


    public void savePost(FreeBoardWriteDTO freeBoardWrite);
    public void updatePost(Long id, FreeBoardDetailDTO freeBoardDetailDTO);
    public void deletePost(Long id);
    public List<FreeBoardPost> getFreeBoardList(FreeBoardListDTO freeBoardListDTO);
    public FreeBoardDetailDTO getDetailBoard(Long boardNo);

}