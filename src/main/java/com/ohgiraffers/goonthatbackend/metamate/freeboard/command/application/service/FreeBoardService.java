package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service;


import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;


import java.util.List;

public interface FreeBoardService {

    public void savePost(FreeBoardWriteDTO boardDTO);
//    public void updatePost();
//    public void deletePost();
    public List<FreeBoardListDTO> getAllPosts();
    public FreeBoardDetailDTO getDetailPosts(Long boardNo);
//    public List<FreeBoardListDTO> getSearchPosts();
}
