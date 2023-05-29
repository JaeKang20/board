package com.portfolio.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComplainRepository extends JpaRepository<Complain, Long> {
    Complain findByBoard(Board board);
    List<Complain> findTop10ByOrderByNumberDesc();
}
