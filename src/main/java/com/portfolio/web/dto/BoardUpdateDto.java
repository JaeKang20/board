package com.portfolio.web.dto;

import lombok.Data;
import lombok.Getter;


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
