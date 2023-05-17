package com.portfolio.domain;

import com.portfolio.web.dto.BoardSearchCond;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.portfolio.domain.QBoard.*;

@Repository
@Transactional
public class BoardRepositoryImp  {
private final JPAQueryFactory query;
    public BoardRepositoryImp(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Board> findAll(BoardSearchCond cond) {
        return query.select(board)
                .from(board)
                .where(
                        likeTitle(cond.getTitle())
                ).fetch();
    }

    public Page<Board> findByNickname(BoardSearchCond cond, Pageable pageable) {
        return (Page<Board>) query.select(board)
                .from(board)
                .where(
                        likeMemberNickname(cond.getNickname(), pageable )
                );
    }

    private BooleanExpression likeTitle(String title) {
        if (StringUtils.hasText(title)) {
            return board.title.like("%" + title + "%");
        }
        return null;
    }

    public BooleanExpression likeMemberNickname(String nickname, Pageable pageable) {
        if (StringUtils.hasText(nickname)) {
            return board.member.nickname.like("%" + nickname + "%");
        }
        return null;
    }
}
