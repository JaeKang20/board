package com.portfolio.service;

import com.portfolio.domain.Board;
import com.portfolio.domain.BoardRepository;
import com.portfolio.web.dto.BoardSearchCond;
import com.portfolio.web.dto.BoardUpdateDto;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class BoardServiceImp implements BoardService{
    private final BoardRepository boardRepository;


    @Override
    public Board save(Board board) {

        board.setRegisterDate(LocalDateTime.now().toString());

        return boardRepository.save(board);
    }


    @Override
    public void update(Long boardId, BoardUpdateDto updateParam) {

    }

    @Override
    public Optional<Board> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Board> findBoards(BoardSearchCond boardSearchCond, Pageable pageable) {
        if (StringUtils.hasText(boardSearchCond.getTitle()) && StringUtils.hasText(boardSearchCond.getContent())) {
            return boardRepository.findByTitleContainingOrContentContaining(boardSearchCond.getTitle(), boardSearchCond.getContent(), pageable);
        } else if (StringUtils.hasText(boardSearchCond.getTitle())) {
            return boardRepository.findByTitleContaining(boardSearchCond.getTitle(), pageable);
        } else if (StringUtils.hasText(boardSearchCond.getNickname())) {
            return boardRepository.findByMember_Nickname(boardSearchCond.getNickname(), pageable);
        } else {
            return boardRepository.findAll(pageable);
        }
    }

    @Override
    public void delete(Long boardId) {

    }

    public Page<Board> getBoards(Pageable pageable) {
        return  null;
    }
}
