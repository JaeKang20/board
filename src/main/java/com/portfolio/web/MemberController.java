package com.portfolio.web;

import com.portfolio.web.dto.MemberSaveDto;
import com.portfolio.domain.Member;
import com.portfolio.service.MemberService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }
//아무 역할 없이 resource/templates/members/createMemberForm.html으로 get요청

    @PostMapping("/add") // 클라이언트로부터 HTTP POST 요청을 받을 때 처리하는 메소드를 지정합니다. "/add" 경로로 요청이 오면 이 메소드가 실행됩니다.
    public String save(@Validated @ModelAttribute MemberSaveDto memberSaveDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { // 유효성 검사에서 에러가 발생했는지 확인합니다.
            return "members/addMemberForm"; // 에러가 있다면, 회원 가입 폼(addMemberForm)으로 돌아갑니다.
        }

        memberService.save(memberSaveDto); // 에러가 없다면, memberService를 통해 회원 정보를 저장합니다.

        return "redirect:/"; // 저장 후, 메인 페이지("/")로 리다이렉트합니다.
    }
}