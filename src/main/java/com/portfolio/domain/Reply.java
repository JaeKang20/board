package com.portfolio.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reply {
    @Id
    @GeneratedValue
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name="reply_content")
    private String replyContent;

    public String getMemberNickname() {
        return member.getNickname();
    }

    @Builder
    public Reply(Long replyId, Board board, Member member, String replyContent) {
        this.replyId = replyId;
        this.board = board;
        this.member = member;
        this.replyContent = replyContent;
    }
}