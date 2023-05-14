package com.portfolio.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@RequiredArgsConstructor
public class LoginDto {
    @NotEmpty
    private final String loginId;
    @NotEmpty
    private final String password;

}
