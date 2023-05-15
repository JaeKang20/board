package com.portfolio.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String getBoardList(Model model) {
        BoardSearchDto boardSearch = new BoardSearchDto(); // 검색 조건을 담을 객체
        List<BoardDto> boards = boardService.getBoardList(); // 게시판 목록 조회

        model.addAttribute("boardSearch", boardSearch);
        model.addAttribute("boards", boards);

        return "boards";
    }


}

