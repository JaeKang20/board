package com.portfolio.controller;

import com.portfolio.controller.dto.MemberSaveDto;
import com.portfolio.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/members")
    public String createMember(@RequestBody MemberSaveDto memberSaveDto) {
        Long memberId = memberService.join(memberSaveDto);
        return "Member created with ID: " + memberId;

    }
}
