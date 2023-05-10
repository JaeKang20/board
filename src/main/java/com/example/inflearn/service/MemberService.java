package com.example.inflearn.service;

import com.example.inflearn.domain.Member;
import com.example.inflearn.domain.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    /*
    * 회원가입 서비스 로직
    * */

    public Long join(Member member){ //dto가 들어가는것이 좋다.
    validateDuplicateMember(member); //중복회원 검증 메서드
    memberRepository.save(member); //회원 가입 저장
    return  member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /*
    * 전체 회원 조회 서비스 로직
    * */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
