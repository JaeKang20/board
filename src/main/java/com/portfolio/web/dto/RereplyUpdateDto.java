package com.portfolio.web.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter @Setter
public class RereplyUpdateDto {

    private String content;

    public RereplyUpdateDto() {}

    public RereplyUpdateDto(String content) {
        this.content = content;
    }
}