package com.portfolio.web;

import com.portfolio.service.ComplainService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RequiredArgsConstructor
@Controller
public class ComplainController {
    private final ComplainService complainService;

    @PostMapping("/boards/{boardId}/complain")
    public String complain(@PathVariable Long boardId) {
        complainService.createComplain(boardId);
        return "redirect:/boards/{boardId}";
    }

}
