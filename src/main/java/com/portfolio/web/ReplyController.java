package com.portfolio.web;

import com.portfolio.domain.Board;
import com.portfolio.domain.Reply;
import com.portfolio.service.BoardService;
import com.portfolio.service.ReplyService;
import com.portfolio.web.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;


    //댓글등록
    @PostMapping("boards/{boardId}/reply")
    public String reply(@PathVariable Long boardId, ReplyDto replyDto){
        replyService.createReply(boardId,replyDto);
        return "redirect:/boards/{boardId}";
    }
    // 댓글 수정
    @PutMapping("/boards/{boardId}/reply/{replyId}")
    public String editReply(@PathVariable Long boardId, @PathVariable Long replyId, @RequestBody ReplyDto replyDto){
        replyService.editReply(replyId, replyDto);
        return "redirect:/boards/{boardId}";
    }

    // 댓글 삭제
    @DeleteMapping("/boards/{boardId}/reply/{replyId}")
    public String deleteReply(@PathVariable Long boardId, @PathVariable Long replyId){
        replyService.deleteReply(replyId);
        return "redirect:/boards/{boardId}";
    }

}
