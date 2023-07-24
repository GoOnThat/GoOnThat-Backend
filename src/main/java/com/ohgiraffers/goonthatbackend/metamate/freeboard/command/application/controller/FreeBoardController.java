package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.controller;

import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.dto.FreeBoardCommentReadDTO;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.application.service.FreeBoardCommentService;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.repository.FreeBoardCommentRepository;
import com.ohgiraffers.goonthatbackend.metamate.common.MD5Generator;
import com.ohgiraffers.goonthatbackend.metamate.file.command.application.dto.FileDTO;
import com.ohgiraffers.goonthatbackend.metamate.file.command.application.service.FileService;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardDetailDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardEditDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardListDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto.FreeBoardWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.FreeBoardPostService;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardPostService freeBoardService;
    private final FreeBoardCommentService commentService;
    private final FileService fileService;

    /* 게시판 전체 목록 조회 */
    @GetMapping("/list")
    public String list(@LoginUser SessionMetaUser user, @PageableDefault(page=0, size=10, sort="boardNo",
                        direction= Sort.Direction.DESC) Pageable pageable, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        Page<FreeBoardListDTO> boardList = freeBoardService.getAllPosts(pageable);

        int nowPage = boardList.getPageable().getPageNumber();
        int startPage= Math.max(nowPage -4, 1);
        int endPage= Math.min(nowPage+9, boardList.getTotalPages());

        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    /* 글쓰기 페이지 조회 */
    @GetMapping("/write")
    public String write(@ModelAttribute("freeBoardWriteDTO") FreeBoardWriteDTO freeBoardWriteDTO,
                        @LoginUser SessionMetaUser user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "board/write";
    }

    /* 글쓰기 페이지 작성 */
    @PostMapping("/write")
    public String writeSave(@ModelAttribute("freeBoardWriteDTO") FreeBoardWriteDTO freeBoardWriteDTO,
                            @RequestParam("file") MultipartFile files,
                            @LoginUser SessionMetaUser user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        }

        try {
            String originFileName = files.getOriginalFilename();
            String fileName = new MD5Generator(originFileName).toString();
            String savePath = System.getProperty("user.dir") + "\\files";

            if(!new File(savePath).exists()) {
                new File(savePath).mkdirs();
            }

            String filePath = savePath + "\\" + fileName;
            files.transferTo(new File(filePath));

            FileDTO fileDTO = new FileDTO();
            fileDTO.setOriginFileName(originFileName);
            fileDTO.setFilename(fileName);
            fileDTO.setFilePath(filePath);

            Long fileNo = fileService.saveFile(fileDTO);
            freeBoardWriteDTO.setFileNo(fileNo);
            freeBoardService.savePost(freeBoardWriteDTO, user);


        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/board/list";
    }

    /* 게시판 글 번호 별 세부 조회 */
    @GetMapping("/detail/{boardNo}")
    public String detail(@PathVariable Long boardNo, @ModelAttribute("freeBoardDetailDTO") FreeBoardDetailDTO freeBoardDetailDTO,
                         @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        FreeBoardDetailDTO boardDetail = freeBoardService.getDetailPosts(boardNo);
        freeBoardService.hitsUp(boardNo,boardDetail);

        if (boardDetail.isBoardIsDeleted()) {
            return "redirect:board/list";
        }

        model.addAttribute("boardDetail", boardDetail);

        return "board/detail";
    }

    /* 게시글 수정 페이지 조회 */
    @GetMapping("/edit/{boardNo}")
    public String edit(@PathVariable Long boardNo, @ModelAttribute("freeBoardEditDTO") FreeBoardEditDTO freeBoardEditDTO,
                       @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }

        FreeBoardDetailDTO boardDetail = freeBoardService.getDetailPosts(boardNo);
        if (boardDetail.getMetaUser().getId().equals(user.getId())) {
            model.addAttribute("boardDetail", boardDetail);
            freeBoardEditDTO.setBoardTitle(boardDetail.getBoardTitle());
            freeBoardEditDTO.setBoardContent(boardDetail.getBoardContent());
            freeBoardEditDTO.setBoardCategory(boardDetail.getBoardCategory());
            return "board/edit";
        } else {
            model.addAttribute("Message", "게시글을 수정할 권한이 없습니다.");
            return "redirect:/board/detail/" + boardNo;
        }
    }

    /* 게시글 수정 */
    @PostMapping("/edit/{boardNo}")
    public String editSave(@PathVariable Long boardNo, @ModelAttribute("freeBoardEditDTO") FreeBoardEditDTO freeBoardEditDTO,
                           @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }

        String message = freeBoardService.updatePost(boardNo, freeBoardEditDTO, user);
        model.addAttribute("Message", message);

        return "redirect:/board/detail/" + boardNo;
    }

    /* 게시글 삭제 */
    @PostMapping(value = "/detail/{boardNo}")
    public String delete(@PathVariable Long boardNo, @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        String message = freeBoardService.deletePost(boardNo, user);
        model.addAttribute("Message", message);

        if (message.equals("게시글이 삭제되었습니다.")) {
            return "redirect:/board/list";
        } else {
            return "redirect:/board/detail/" + boardNo;
        }

    }

    /* 첨부파일 다운로드 */
    @GetMapping("/dowmload/{fileNo}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileNo") Long fileNo) throws IOException {

        FileDTO fileDTO = fileService.getFile(fileNo);
        Path path = Paths.get(fileDTO.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDTO.getOriginFileName() + "\"").body(resource);
    }
}
