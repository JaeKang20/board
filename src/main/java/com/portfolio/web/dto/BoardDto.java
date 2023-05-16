package com.portfolio.web.dto;

import com.portfolio.domain.Board;
import com.portfolio.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardDto {
    private Long boardId;
    private Member member;
    private String title;
    private String content;
    private String registerDate;
    private String updateDate;
    private String deleteDate;
    private int boardViewCount;
    private int boardLike;
    @Builder
    public BoardDto(Long boardId, Member member, String title, String content, String registerDate, String updateDate, String deleteDate, int boardViewCount, int boardLike) {
        this.boardId = boardId;
        this.member = member;
        this.title = title;
        this.content = content;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
        this.boardViewCount = boardViewCount;
        this.boardLike = boardLike;
    }

    public Board toEntity(){
        return Board.builder().
                boardId(boardId)
                .member(member)
                .title(title)
                .content(content)
                .registerDate(registerDate)
                .updateDate(updateDate)
                .deleteDate(deleteDate)
                .boardlike(boardLike)
                .build();

    }
}
