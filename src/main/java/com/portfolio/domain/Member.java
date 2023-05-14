package com.portfolio.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter@Setter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)가 @Id 어노테이션과 함께 사용되면,
    //데이터베이스에서 자동으로 생성되는 primary key 값을 사용하여 엔티티 객체를 생성
    private  Long id;

    @NotBlank
    @Size(min = 4, message = "아이디는 최소 4자 이상이어야 합니다.")
    private String memberId;

    @NotBlank
    @Size(min = 4, message = "비밀번호는 최소 4자 이상이어야 합니다.")
    private String memberPassword;
    @NotNull
    private String nickname;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String emailAddress;

    private String joinDate;

    @Builder
    public Member(Long id, String memberId,String memberPassword, String nickname, String emailAddress, String joinDate)  {
    this.id = id;
    this.memberId = memberId;
    this.memberPassword = memberPassword;
    this.nickname = nickname;
    this.emailAddress = emailAddress;
    this.joinDate = joinDate;
    }

}
