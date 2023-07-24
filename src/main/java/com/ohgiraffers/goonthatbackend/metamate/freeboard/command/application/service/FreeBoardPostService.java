package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service;


import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardEditDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;

import java.util.List;

public interface FreeBoardPostService {


    String savePost(FreeBoardWriteDTO boardDTO, SessionMetaUser user);

    String updatePost(Long boardNo, FreeBoardEditDTO freeBoardEditDTO, SessionMetaUser user);

    String deletePost(Long boardNo, SessionMetaUser user);

    List<FreeBoardListDTO> getAllPosts();

    FreeBoardDetailDTO getDetailPosts(Long boardNo);

    void hitsUp(Long boardNo, FreeBoardDetailDTO freeBoardDetailDTO);
//     List<FreeBoardListDTO> getSearchPosts();
}
