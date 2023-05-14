package com.portfolio.domain;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class MemberSearchDto {

    private final String memberId;
    private final String memberPassword;
}