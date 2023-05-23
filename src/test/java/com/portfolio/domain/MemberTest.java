package com.portfolio.domain;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    void createMember() {
        // Given
        Long id = 1L;
        String memberId = "sampleId";
        String memberPassword = "samplePassword";
        String nickname = "sampleNickname";
        String emailAddress = "sample@example.com";
        String joinDate = "2023-05-21";

        // When
        Member member = Member.builder()
                .id(id)
                .memberId(memberId)
                .memberPassword(memberPassword)
                .nickname(nickname)
                .emailAddress(emailAddress)
                .joinDate(joinDate)
                .build();

        // Then
        assertThat(member.getId()).isEqualTo(id);
        assertThat(member.getMemberId()).isEqualTo(memberId);
        assertThat(member.getMemberPassword()).isEqualTo(memberPassword);
        assertThat(member.getNickname()).isEqualTo(nickname);
        assertThat(member.getEmailAddress()).isEqualTo(emailAddress);
        assertThat(member.getJoinDate()).isEqualTo(joinDate);
    }
}
