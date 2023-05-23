package com.portfolio.web.board;

import com.portfolio.config.SessionConst;
import com.portfolio.domain.*;

import com.portfolio.service.*;
import com.portfolio.web.dto.BoardSearchCond;


import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    @GetMapping
    public String boards(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                         @ModelAttribute("boardSearch") BoardSearchCond boardSearchCond,
                         Model model,@PageableDefault(size = 5, direction = Sort.Direction.DESC, sort = "boardId") Pageable pageable) {
        // 로그인한 회원 정보를 세션에서 가져옵니다.
        // 세션에 로그인 정보가 없을 수도 있으므로 required = false로 설정합니다.
        Page<Board> boardPage;
        if (StringUtils.hasText(boardSearchCond.getTitle()) || StringUtils.hasText(boardSearchCond.getNickname())|| StringUtils.hasText(boardSearchCond.getContent())) {
            boardPage = boardService.findBoards(boardSearchCond, pageable);
            //findBoards 메서드 실행하여 조건이 있으면 먼저 실행
        } else {
            boardPage = boardService.getBoards(pageable);
        }  // 조건이 없다면 게시판을 페이징하여 가져옵니다. getBoards는 최신 순으로 나열합니다.

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
        Board board = boardService.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        model.addAttribute("board", board);
        boardService.increaseViewCount(boardId);
        return "board";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model, //PathVariable은 {}에 들어갈 내용이다.
                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {


        Board board = boardService.findById(boardId).get();
        if (!loginMember.getMemberId().equals(board.getMember().getMemberId())) {
            System.out.println(loginMember.getMemberId()+"and"+board.getMember().getMemberId());
            System.out.println(loginMember.getMemberId().compareTo(board.getMember().getMemberId()));
            return "redirect:/error/403";
        }
        model.addAttribute("board", board);
        return "editForm";
    }



}