package com.portfolio.web;

import com.portfolio.config.SessionConst;
import com.portfolio.domain.Board;
import com.portfolio.domain.Member;
import com.portfolio.service.BoardService;

import com.portfolio.web.dto.BoardSearchCond;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boards(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                         @ModelAttribute("boardSearch") BoardSearchCond boardSearchCond,
                         Model model,
                         @PageableDefault(size = 5, direction = Sort.Direction.DESC, sort = "boardId") Pageable pageable) {
        // 로그인한 회원 정보를 model에 추가
        model.addAttribute("loginMember", loginMember);

        // BoardService를 이용하여 게시물 목록을 가져옵니다.
        Page<Board> boards = boardService.getBoards(pageable);

        // 가져온 게시물 목록을 model에 추가합니다.
        model.addAttribute("boards", boards);

        // 게시판 검색 조건을 model에 추가합니다.
        model.addAttribute("boardSearch", boardSearchCond);

        return "boards";
    }
    @GetMapping("/add")
    public String writeForm(Model model) {
        BoardSearchCond boardSearchCond = new BoardSearchCond();
        model.addAttribute("form", boardSearchCond);
        return "addForm";
    }


}

