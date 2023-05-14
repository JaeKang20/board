package com.portfolio.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findByMemberIdAndMemberPassword(String memberId, String memberPassword);
    }
