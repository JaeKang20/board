package com.portfolio.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {//Spring Data JPA를 적용하지 않았습니다. 향후

    Optional<Member> findByName(String name);

}
