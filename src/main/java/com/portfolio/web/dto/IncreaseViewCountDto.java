package com.portfolio.web.dto;

import com.portfolio.domain.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IncreaseViewCountDto {
    private Long boardId;
    private int boardViewCount;


    public Board toEntity(){
        return Board.builder()
                .boardId(boardId)
                .boardViewCount(boardViewCount)
                .build();
    }
}
