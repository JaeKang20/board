package com.portfolio.web.dto;

import com.portfolio.domain.Member;
import lombok.Builder;

import java.time.LocalDateTime;


//웹 등록 화면에서 데이터를 전달받을 폼 객체 (dto)
// MemberSaveDto 클래스
public class MemberSaveDto {
    private String memberId;
    private String memberPassword;
    private String nickname;
    private String emailAddress;


    @Builder
    public MemberSaveDto(String memberId, String memberPassword, String nickname, String emailAddress){
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.nickname = nickname;
        this.emailAddress = emailAddress;
    }

    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .memberPassword(memberPassword)
                .nickname(nickname)
                .emailAddress(emailAddress)
                .joinDate(LocalDateTime.now().toString())// Dto 객체를 엔티티로 변환할 때, 회원 가입 시간을 현재 시간으로 설정
                .build();
    }
}

