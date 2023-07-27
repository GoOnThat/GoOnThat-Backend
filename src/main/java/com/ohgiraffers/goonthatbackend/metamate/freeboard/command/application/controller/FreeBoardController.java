package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.controller;

import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.file.command.application.dto.MultiFilesReadDTO;
import com.ohgiraffers.goonthatbackend.metamate.file.command.application.dto.MultiFilesWriteDTO;
import com.ohgiraffers.goonthatbackend.metamate.file.command.application.service.MultiFilesService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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
    private final MultiFilesService multiFilesService;

    /* 게시판 전체 목록 조회, 검색목록 조회 분리 */
    @GetMapping("/list")
    public String list(@RequestParam(required = false) String searchKeyword,
                       @RequestParam(required = false) String key,
                       @PageableDefault(page = 0, sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable
            , Model model) {

        Page<FreeBoardListDTO> boardList;

        if (searchKeyword != null && key != null) {
            boardList = freeBoardService.getSearchPosts(key, searchKeyword, pageable);
        } else {
            boardList = freeBoardService.getAllPosts(pageable);
        }

        int nowPage = boardList.getPageable().getPageNumber() + 1; // 현재 페이지 번호를 1부터 시작하도록 수정
        int totalPages = boardList.getTotalPages();
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, totalPages);
        endPage = Math.min(endPage, startPage + 9); // 최대 10개 페이지 링크를 표시하도록 수정

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", boardList);
        model.addAttribute("totalPages", totalPages);

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
    public String writeSave(@Valid FreeBoardWriteDTO freeBoardWriteDTO, BindingResult bindingResult,
                            @RequestParam("file") List<MultipartFile> files,
                            @LoginUser SessionMetaUser user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            model.addAttribute("freeBoardWriteDTO", freeBoardWriteDTO);
            return "board/write"; // 에러가 발생한 폼 페이지로 다시 이동
        }

        try {
            for (MultipartFile file : files) {
                multiFilesService.saveFile(file,freeBoardWriteDTO,user);
            }

            freeBoardService.savePost(freeBoardWriteDTO, user);

        } catch (IOException e) {
            model.addAttribute("errorMessage", "파일 전송 중 오류가 발생했습니다.");
            return "board/write";
        } catch (NoSuchAlgorithmException e) {
            model.addAttribute("errorMessage", "파일 처리 중 오류가 발생했습니다.");
            return "board/write";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "board/write";
        }
        return "redirect:/board/list";
    }

    /* 게시판 글 번호 별 세부 조회 */
    @GetMapping("/detail/{boardNo}")
    public String detail(@PathVariable Long boardNo, @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        FreeBoardDetailDTO boardDetail = freeBoardService.getDetailPosts(boardNo);
        freeBoardService.hitsUp(boardNo, boardDetail);

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
    public String editSave(@PathVariable Long boardNo,
                           @ModelAttribute("freeBoardEditDTO")
                           @Valid FreeBoardEditDTO freeBoardEditDTO, BindingResult bindingResult,
                           @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }
        FreeBoardDetailDTO boardDetail = freeBoardService.getDetailPosts(boardNo);
        if (bindingResult.hasErrors()) {
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            model.addAttribute("boardDetail", boardDetail);
            model.addAttribute("freeBoardEditDTO", freeBoardEditDTO);
            return "board/edit";
        }
        try {
            freeBoardService.updatePost(boardNo, freeBoardEditDTO, user);
            return "redirect:/board/detail/" + boardNo;
        } catch (IllegalStateException e) {
            return "redirect:/board/detail/" + boardNo;
        }
    }

    /* 게시글 삭제 */
    @PostMapping(value = "/detail/{boardNo}")
    public String delete(@PathVariable Long boardNo, @LoginUser SessionMetaUser user, Model model) {

        if (user != null) {
            model.addAttribute("user", user);
        }

        try {
            freeBoardService.deletePost(boardNo, user);
            return "redirect:/board/list";
        } catch (IllegalStateException e) {
            return "redirect:/board/detail/" + boardNo;
        }
    }

    /* 첨부파일 다운로드 */
    @GetMapping("/download/{fileNo}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileNo") Long fileNo) throws IOException {

        MultiFilesReadDTO fileDTO = multiFilesService.getFile(fileNo);
        Path path = Paths.get(fileDTO.getFilePath());

        // 파일을 InputStreamResource로 감싸서 리소스로 만듭니다.
        Resource resource = new InputStreamResource(Files.newInputStream(path));

        // ResponseEntity를 이용하여 다운로드 응답을 생성합니다.
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDTO.getOriginFileName() + "\"")
                .body(resource);
    }
}
