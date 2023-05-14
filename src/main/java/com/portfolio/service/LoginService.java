package com.portfolio.service;

import com.portfolio.domain.Member;
import com.portfolio.domain.MemberRepository;
import com.portfolio.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member login(LoginDto loginDto) {
        Optional<Member> member = memberRepository.findByMemberIdAndMemberPassword(loginDto.getLoginId(), loginDto.getPassword());
        return member.orElse(null);
    }

}
