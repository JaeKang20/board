package com.portfolio.web;

import com.portfolio.config.argumentresolver.LoginUserAuthorize;

import com.portfolio.domain.Member;
import com.portfolio.service.ReplyService;
import com.portfolio.web.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;

//댓글 등록
    @PostMapping("/boards/{boardId}/reply")
    public String createReply(@PathVariable Long boardId, @ModelAttribute ReplyDto replyDto, @LoginUserAuthorize Member currentUser){
        replyService.createReply(boardId, replyDto, currentUser);
        return "redirect:/boards/{boardId}";
    }

    // 댓글 수정
    @PutMapping("/boards/{boardId}/reply/{replyId}")
    public String editReply(@PathVariable Long boardId, @PathVariable Long replyId, @RequestBody ReplyDto replyDto,
                            @LoginUserAuthorize Member currentUser){
        replyService.editReply(replyId, replyDto, currentUser);
        return "redirect:/boards/{boardId}";
    }

    // 댓글 삭제
    @DeleteMapping("/boards/{boardId}/reply/{replyId}")
    public String deleteReply(@PathVariable Long boardId, @PathVariable Long replyId,
                              @LoginUserAuthorize Member currentUser) {
        replyService.deleteReply(replyId, currentUser);
        return "redirect:/boards/{boardId}";
    }

}