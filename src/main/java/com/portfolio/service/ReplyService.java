package com.portfolio.service;

import com.portfolio.domain.Reply;
import com.portfolio.domain.ReplySearchCond;
import com.portfolio.web.dto.ReplyUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ReplyService {
    Reply save(Reply reply);

    void update(Long replyId, ReplyUpdateDto updateParam);

    Optional<Reply> findById(Long id);

    List<Reply> findReply(ReplySearchCond cond);
}
