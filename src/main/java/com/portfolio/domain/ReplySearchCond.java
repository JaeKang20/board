package com.portfolio.domain;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class ReplySearchCond {
    private String replyContent;
    private Long boardId;


    public ReplySearchCond() {

    }

    public ReplySearchCond(String replyContent, Long boardId) {
        this.replyContent = replyContent;
        this.boardId = boardId;
    }
}