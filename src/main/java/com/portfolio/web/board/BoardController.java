package com.portfolio.web.board;

import com.portfolio.config.SessionConst;

import com.portfolio.config.argumentresolver.LoginUserAuthorize;
import com.portfolio.domain.*;

import com.portfolio.domain.dto.MemberSearchDto;
import com.portfolio.service.*;
import com.portfolio.web.dto.BoardSearchCond;


import lombok.extern.slf4j.Slf4j;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final ComplainService complainService;
    private final ReplyService replyService;
    @GetMapping
    public String boards(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            @ModelAttribute("boardSearch") BoardSearchCond boardSearchCond,
            Model model,
            @PageableDefault(size = 5,direction = Sort.Direction.DESC, sort = "boardId") Pageable pageable) {

        Page<Board> boardPage;
        if (StringUtils.hasText(boardSearchCond.getTitle())
                || StringUtils.hasText(boardSearchCond.getNickname())
                || StringUtils.hasText(boardSearchCond.getContent())) {
            boardPage = boardService.findBoards(boardSearchCond, pageable);
        } else {
            boardPage = boardService.findAllSortedByAdminStatus(pageable);
        }

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
        boardService.increaseViewCount(boardId); // 조회수 증가 처리

        Board board = boardService.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        List<Reply> replies = replyService.findByBoardId(boardId);
        model.addAttribute("board", board);
        model.addAttribute("replies", replies);

        // 날짜 포맷 변경
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh시 mm분", Locale.KOREAN);

        // registerDate가 null이 아닌지 확인
        String formattedRegisterDate = "";
        if (board.getRegisterDate() != null) {
            LocalDateTime parsedDate = LocalDateTime.parse(board.getRegisterDate(), inputFormatter);
            formattedRegisterDate = parsedDate.format(outputFormatter);
        }

        model.addAttribute("formattedRegisterDate", formattedRegisterDate);

        return "board";
    }




    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model,
                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {

        Board board = boardService.findById(boardId).get();
        if (!loginMember.getMemberId().equals(board.getMember().getMemberId())) {
            log.info("Member ID: {} and Board Member ID: {}", loginMember.getMemberId(), board.getMember().getMemberId());
            log.info("Comparison Result: {}", loginMember.getMemberId().compareTo(board.getMember().getMemberId()));
            return "redirect:/error/403";
        }
        model.addAttribute("board", board);
        return "editForm";
    }

    @GetMapping("/management")//관리자 계정이 들어가는 곳.
    public String management(Model model,
                             @LoginUserAuthorize Member admin,
                             MemberSearchDto memberSearchDto) {
        if (admin != null && admin.isAdmin()) {
            List<Board> topTenBoardsView = boardService.getTopTenBoardsView();
            List<Board> topTenBoardsLike = boardService.getTopTenBoardsLike();
            List<Complain> topTenComplain = complainService.getTopTenComplain();
            model.addAttribute("topTenBoardsView", topTenBoardsView);
            model.addAttribute("topTenBoardsLike", topTenBoardsLike);
            model.addAttribute("topTenComplainedBoards",topTenComplain);
            return "members/management";
        } else {
            return "redirect:error/403";
        }
    }


}