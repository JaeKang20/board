package com.portfolio.web.dto;

import com.portfolio.domain.Board;

import com.portfolio.domain.Member;
import lombok.*;

@Data
public class BoardUpdateDto {

    private String title;
    private String content;


    public BoardUpdateDto () {
    }

    public BoardUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}