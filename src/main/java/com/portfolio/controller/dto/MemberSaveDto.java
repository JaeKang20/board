package com.portfolio.controller.dto;

import com.portfolio.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


//웹 등록 화면에서 데이터를 전달받을 폼 객체 (dto)
// MemberSaveDto 클래스
public class MemberSaveDto {
    private String memberId;
    private String memberPassword;
    private String nickname;
    private String emailAddress;
    private String joinDate;

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
                .joinDate(LocalDateTime.now().toString())
                .build();
    }
}

