package com.example.inflearn.controller;

import com.example.inflearn.domain.Member;
import com.example.inflearn.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }//아무 역할 없이 resource/templates/mebers/createMemberForm.html으로 보냄.
    @PostMapping(value = "/members/new")
    //createMemberForm.html에서 Post를 받아서 처리하게될 것임. 서비스 로직에서 회원가입을 저장하게됨.
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}