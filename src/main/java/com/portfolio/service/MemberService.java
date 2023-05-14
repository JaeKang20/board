package com.portfolio.service;



import com.portfolio.domain.MemberSearchDto;
import com.portfolio.web.dto.MemberSaveDto;
import com.portfolio.domain.Member;
import com.portfolio.domain.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    /*
    * 회원가입 서비스 로직
    * */

    @Transactional
    public Long save(MemberSaveDto memberSaveDto) {
        validateDuplicateMember(memberSaveDto); // 중복회원 검증 메서드
        Member member = memberSaveDto.toEntity(); // Member 엔티티로 변환
        Member savedMember = memberRepository.save(member); // 회원 저장
        return savedMember.getId(); // 저장된 회원의 ID 반환
    }

    private void validateDuplicateMember(MemberSaveDto memberSaveDto) {
        Optional<Member> existingMember = memberRepository.findByMemberId(memberSaveDto.toEntity().getMemberId());
        if (existingMember.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /*
     * 전체 회원 조회 서비스 로직
     * */
    public Member findByMembers(MemberSearchDto memberSearchDto) {
        Optional<Member> member = memberRepository.findByMemberIdAndMemberPassword(memberSearchDto.getMemberId(),memberSearchDto.getMemberPassword());
        return member.orElseThrow(() -> new IllegalArgumentException("잘못된 아이디 또는 비밀번호"));
    }
}
