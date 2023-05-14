package com.portfolio.controller;

import com.portfolio.domain.Member;
import com.portfolio.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }//아무 역할 없이 resource/templates/mebers/createMemberForm.html으로 보냄.

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}