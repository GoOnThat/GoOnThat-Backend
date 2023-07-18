package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.controller;

//@Controller
//@RequestMapping("/board")
public class FreeBoardController {
//
//    private final FreeBoardService freeBoardService;
//
//    @Autowired
//    public FreeBoardController(FreeBoardService freeBoardService) {
//        this.freeBoardService = freeBoardService;
//    }

    /* 게시판 전체 목록 조회 */
//    @GetMapping("/list")
//    public String list(Model model, FreeBoardListDTO freeBoardListDTO) {
//        List<FreeBoardPost> boardList = freeBoardService.getAllBoards(freeBoardListDTO);
//        model.addAttribute("boardList", boardList);
//        return "board/list";
//    }

    /* 글쓰기 페이지 조회 */
//    @GetMapping("/write")
//    public ModelAndView write(ModelAndView mv) {
//
//        mv.setViewName("/board/write");
//
//        return mv;
//    }

    /* 글쓰기 페이지 작성 */
//    @PostMapping("/write")
//    public String enrollContent(
//            @LoginUser SessionMetaUser user,
//            @ModelAttribute("user") FreeBoardWriteDTO freeBoardWrite,
//            Model model) {
//
//        if(user==null){
//            return "redirect:/auth/login";
//        }
//
//        freeBoardService.write(freeBoardWrite);
//
//        return "redirect:/board/list";
//    }

    /* 게시판 글 번호 별 세부 조회 */
//    @GetMapping("/detail/{boardNo}")
//    public String detail(@PathVariable Long boardNo, Model model) {
//        FreeBoardDetailDTO freeBoardDetailDTO = freeBoardService.detailBoard(boardNo);
//        System.out.println("Controller = " + freeBoardDetailDTO);
//        model.addAttribute("detailBoard", freeBoardDetailDTO);
//        System.out.println(freeBoardDetailDTO);
//        return "board/detail";
//    }

//    @PostMapping("/detail")
//    public String detail(Model model, FreeBoardDetailDTO freeBoardDetailDTO) {
//
//        String boardTitle = freeBoardDetailDTO.getBoardTitle();
//        String boardContent = freeBoardDetailDTO.getBoardContent();
//        String boardCategory = freeBoardDetailDTO.getBoardCategory();
//
//        model.addAttribute("boardTitle", boardTitle);
//        model.addAttribute("boardContent", boardContent);
//        model.addAttribute("boardCategory", boardCategory);
//
//        freeBoardService.enrolledwrite(freeBoardDetailDTO);
//
//        return "board/detail";
    }

//}

