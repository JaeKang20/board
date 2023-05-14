package com.portfolio.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class MemberSearchCond {
    private String nickname;
    private String memberId;
    private String password;
}