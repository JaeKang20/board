package com.portfolio.web.dto;

import com.portfolio.domain.Board;
import com.portfolio.domain.Member;
import com.portfolio.domain.Reply;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class ReplyDto {
    private Long replyId;
    private Board board;
    private String memberId; // Member 객체 대신 회원 ID를 사용
    private String replyContent;

    @Builder
    ReplyDto(Long replyId, Board board, String memberId, String replyContent) {
        this.replyId=replyId;
        this.board = board;
        this.memberId = memberId;
        this.replyContent = replyContent;
    }
    public Reply toEntity(Member member){
        return Reply.builder().replyId(replyId).board(board).member(member).replyContent(replyContent)
                .build();
    }
}