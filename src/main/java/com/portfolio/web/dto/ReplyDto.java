package com.portfolio.web.dto;

import com.portfolio.domain.Board;
import com.portfolio.domain.Reply;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class ReplyDto {
    private Long replyId;
    private Board board;
    private String replyContent;

    @Builder
    ReplyDto(Long replyId, Board board, String replyContent){
        this.replyId=replyId;
        this.board = board;
        this.replyContent = replyContent;
    }
    public Reply toEntity(){
        return Reply.builder().replyId(replyId).board(board).replyContent(replyContent)
                .build();
    }
}
