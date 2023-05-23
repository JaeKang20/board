package com.portfolio.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

    Page<Board> findByContentContaining(String content, Pageable pageable);
    Page<Board> findByTitleContaining(String title, Pageable pageable);

    Page<Board> findByMember_Nickname(String nickname, Pageable pageable);
    Optional<Board> findById(Long boardId);
    @Override
    void deleteById(Long aLong);

}
