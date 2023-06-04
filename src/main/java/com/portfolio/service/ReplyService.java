package com.portfolio.service;

import com.portfolio.domain.*;
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
    public void createReply(Long boardId, ReplyDto replyDto, Member currentUser){
        Board nowBoard = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board Not found"));
        replyDto.setBoard(nowBoard);
        replyRepository.save(replyDto.toEntity(currentUser));
    }


    public List<Reply> findByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board Not found"));
        return replyRepository.findByBoard(board);
    }

    public void editReply(Long replyId, ReplyDto replyDto, Member currentUser) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new RuntimeException("Reply Not found"));
        Board board = reply.getBoard();
        // 댓글 작성자의 ID와 현재 사용자의 ID 비교
        if (!reply.getMember().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Not authorized to edit this reply.");
        }


        reply.setReplyContent(replyDto.getReplyContent());
        replyRepository.save(reply);
    }

    @Transactional
    public void deleteReply(Long replyId, Member currentUser) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new RuntimeException("Reply Not found"));

        // 댓글 작성자와 현재 사용자를 비교
        if (!reply.getMember().equals(currentUser) && !currentUser.isAdmin()) {
            throw new RuntimeException("Not authorized to delete this reply.");
        }

        replyRepository.deleteById(replyId);
    }

}
