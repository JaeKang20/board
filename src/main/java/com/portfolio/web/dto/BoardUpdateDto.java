package com.portfolio.web.dto;

import com.portfolio.domain.Board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardUpdateDto {
    private String registerDate;
    private String title;
    private String content;
public BoardUpdateDto(){

}
    public Board toEntity(){
        return Board.builder().registerDate(registerDate)
        .title(title)
        .content(content).
        build();
    }
}
