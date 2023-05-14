package com.portfolio.service;

import com.portfolio.domain.Member;
import com.portfolio.domain.MemberRepository;
import com.portfolio.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;


    public Member login(LoginDto loginDto) {
        return memberRepository.findByMemberId(loginDto.getLoginId())
                .filter(m -> m.getMemberPassword().equals(loginDto.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디 또는 패스워드입니다."));
    }

}
