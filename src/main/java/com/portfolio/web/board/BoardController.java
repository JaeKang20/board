package com.portfolio.web.board;

import com.portfolio.config.SessionConst;
import com.portfolio.domain.*;

import com.portfolio.service.*;
import com.portfolio.web.dto.BoardSearchCond;
import com.portfolio.web.dto.ReplyUpdateDto;
import com.portfolio.web.dto.RereplyUpdateDto;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final BoardServiceImp boardServiceImp;
    private final ReplyService replyService;
    private final ReplyServiceImpl replyServiceImpl;
    private final RereplyService rereplyService;

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
    public String board(@PathVariable long boardId, @ModelAttribute ReplySearchCond replySearchCond, @ModelAttribute RereplySearchCond rereplySearchCond, Model model) {
        Board board = boardService.findById(boardId).get();
        List<Reply> reply = replyService.findReply(replySearchCond);
        List<Rereply> rereplys = rereplyService.findRereplys(rereplySearchCond);
        ReplyUpdateDto form = new ReplyUpdateDto();
        RereplyUpdateDto rereplyForm = new RereplyUpdateDto();
        model.addAttribute("replyForm", form);
        model.addAttribute("board", board);
        model.addAttribute("reply", reply);
        model.addAttribute("rereplyForm", rereplyForm);
        model.addAttribute("rereply", rereplys);
        boardServiceImp.increaseViewCount(boardId);
        return "board";
    }
    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model,
                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {


        Board board = boardServiceImp.findById(boardId).get();
        if (loginMember.getMemberId() != board.getMember().getMemberId()) {
            return "redirect:/error/403";
        }
        model.addAttribute("board", board);
        return "editForm";
    }
}
