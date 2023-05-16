package com.portfolio.service;


import com.portfolio.domain.Rereply;
import com.portfolio.domain.RereplySearchCond;
import com.portfolio.web.dto.RereplyUpdateDto;

import java.util.List;
import java.util.Optional;

public interface RereplyService {

    Rereply save(Rereply rereply);

    void update(Long rereplyId, RereplyUpdateDto updateDto);

    Optional<Rereply> findById(Long id);

    List<Rereply> findRereplys(RereplySearchCond cond);
}