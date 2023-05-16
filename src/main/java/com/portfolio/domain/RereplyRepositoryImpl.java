package com.portfolio.domain;


import com.portfolio.domain.q.QRereply;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import static com.portfolio.domain.QRereply.*;

@Repository
@Transactional
public class RereplyRepositoryImpl implements RereplyRepository{

    private final JPAQueryFactory query;

    public RereplyRepositoryImpl(EntityManager entityManager) {
        this.query = new JPAQueryFactory(entityManager);
    }

    public List<Rereply> findAll(RereplySearchCond cond) {
        List<Rereply> fetch = query.select(rereply)
                .from(rereply)
                .where(
                        replyIdEq(cond.getReplyId())
                )
                .fetch();

        return fetch;

    }

    public BooleanExpression replyIdEq(Long replyId) {
        return replyId == null ? null : rereply.originReply.replyId.eq(replyId);
    }

    @Override
    public List<Rereply> findAll() {
        return null;
    }

    @Override
    public List<Rereply> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Rereply> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Rereply> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Rereply entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Rereply> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Rereply> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Rereply> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Rereply> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Rereply> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Rereply> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Rereply> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Rereply getOne(Long aLong) {
        return null;
    }

    @Override
    public Rereply getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Rereply> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Rereply> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Rereply> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Rereply> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Rereply> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Rereply> boolean exists(Example<S> example) {
        return false;
    }
}