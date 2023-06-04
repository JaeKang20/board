package com.portfolio.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter@NoArgsConstructor@Entity
public class Reply {
    @Id
    @GeneratedValue
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name="reply_content")
    private String replyContent;

    @Builder
    public Reply(Long replyId, Board board, String replyContent){
        this.replyId = replyId;
        this.board = board;
        this.replyContent = replyContent;

    }
}
