package com.portfolio.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    public List<Reply> findByBoard(Board board);

}
