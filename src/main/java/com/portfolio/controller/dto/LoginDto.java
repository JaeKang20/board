package com.portfolio.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginDto {
    private final String loginId;
    private final String password;

}
