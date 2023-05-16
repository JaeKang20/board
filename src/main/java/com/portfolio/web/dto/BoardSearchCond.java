package com.portfolio.web.dto;

import lombok.*;


@Data
@Getter @Setter
@NoArgsConstructor
public class BoardSearchCond {

    private String title;
    private String nickname;

    private String content;
    private int boardLike;
    private int boardViewCount;

    public BoardSearchCond(String title, String nickname, String content) {
        this.title = title;
        this.nickname = nickname;
        this.content = content;
    }
}