package com.portfolio.controller;

import com.portfolio.controller.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {


    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginDto form) {
        return "login/loginForm";
    }
}
