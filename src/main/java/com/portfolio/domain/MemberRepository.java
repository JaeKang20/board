package com.portfolio.domain;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {//Spring Data JPA를 적용하지 않았습니다. 향후
    //Spring Data JPA를 적용하면 간단한 CRUD 기능은 메서드로 저장되어있습니다.
    Member save (Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    Optional<Member> findByName(String name);
}
