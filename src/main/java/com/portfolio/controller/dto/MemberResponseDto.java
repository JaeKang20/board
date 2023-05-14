package com.portfolio.controller.dto;

import com.portfolio.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String memberId;
    private String memberPassword;
    private String nickname;
    private String emailAddress;

    public MemberResponseDto(Member entity){
        this.id=getId();
        this.memberId=getMemberId();
        this.memberPassword =getMemberPassword();
        this.nickname = getNickname();
        this.emailAddress = getEmailAddress();
    }
}
