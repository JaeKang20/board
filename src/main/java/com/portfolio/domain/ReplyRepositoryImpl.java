package com.portfolio.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.portfolio.domain.q.QReply.reply;


@Repository
@Transactional
public class ReplyRepositoryImpl {

    private final JPAQueryFactory query;

    public ReplyRepositoryImpl(EntityManager entityManager) {
        this.query = new JPAQueryFactory(entityManager);
    }


    public List<Reply> findAll(ReplySearchCond cond) {


        List<Reply> fetch = query.select(reply)
                .from(reply)
                .where(
                        boardIdEq(cond.getBoardId())
                )
                .fetch();

        return fetch;

    }


    private BooleanExpression boardIdEq(Long boardId) {
        return boardId == null ? null : reply.board.boardId.eq(boardId);
    }




}