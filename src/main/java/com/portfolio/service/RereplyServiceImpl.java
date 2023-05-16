package com.portfolio.service;

import com.portfolio.domain.Rereply;
import com.portfolio.domain.RereplyRepository;
import com.portfolio.domain.RereplyRepositoryImpl;
import com.portfolio.domain.RereplySearchCond;
import com.portfolio.web.dto.RereplyUpdateDto;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RereplyServiceImpl implements RereplyService{

    private final RereplyRepository rereplyRepository;
    private final RereplyRepositoryImpl rereplyRepositoryImpl;


    @Override
    public Rereply save(Rereply rereply) {
        rereply.setRegisterDate(LocalDateTime.now().toString());
        return rereplyRepository.save(rereply);
    }

    @Override
    public void update(Long rereplyId, RereplyUpdateDto updateDto) {
        Rereply rereply = findById(rereplyId).orElseThrow();
        rereply.setUpdateDate(LocalDateTime.now().toString());
        rereply.setRereplyContent(updateDto.getContent());
    }

    @Override
    public Optional<Rereply> findById(Long id) {
        return rereplyRepository.findById(id);
    }

    @Override
    public List<Rereply> findRereplys(RereplySearchCond cond) {
        return rereplyRepositoryImpl.findAll(cond);
    }
}