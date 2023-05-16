package com.portfolio.web.board;

import com.portfolio.config.SessionConst;
import com.portfolio.domain.Board;
import com.portfolio.domain.Member;

import com.portfolio.service.BoardService;
import com.portfolio.service.BoardServiceImp;
import com.portfolio.web.dto.BoardSearchCond;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final BoardServiceImp boardServiceImp;

    @GetMapping
    public String boards(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                         @ModelAttribute("boardSearch") BoardSearchCond boardSearchCond,
                         Model model,@PageableDefault(size = 5, direction = Sort.Direction.DESC, sort = "boardId") Pageable pageable) {
        Page<Board> boardPage = boardService.findBoards(boardSearchCond, pageable);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("boards", boardPage);
        return "boards";
    }
    @GetMapping("/add")
    public String writeForm(Model model) {
        BoardSearchCond boardSearchCond = new BoardSearchCond();
        model.addAttribute("form", boardSearchCond);
        return "addForm";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        Board board = boardService.findById(boardId).get();
        model.addAttribute("board", board);
        return "board";
    }
}

