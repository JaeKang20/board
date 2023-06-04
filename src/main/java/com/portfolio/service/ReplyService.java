package com.portfolio.service;

import com.portfolio.domain.Board;
import com.portfolio.domain.BoardRepository;
import com.portfolio.domain.Reply;
import com.portfolio.domain.ReplyRepository;
import com.portfolio.web.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ReplyService {
    private  final ReplyRepository replyRepository;
    private  final BoardRepository boardRepository;

    @Transactional
    public void createReply(Long boardId, ReplyDto replyDto){
        Board nowBoard = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board Not found"));
        replyDto.setBoard(nowBoard);
        replyRepository.save(replyDto.toEntity());
    }
    public List<Reply> findByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board Not found"));
        return replyRepository.findByBoard(board);
    }

    @Transactional
    public void editReply(Long replyId, ReplyDto replyDto){
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new RuntimeException("Reply Not found"));
        reply.setReplyContent(replyDto.getReplyContent());
    }

    @Transactional
    public void deleteReply(Long replyId){
        replyRepository.deleteById(replyId);
    }


}
