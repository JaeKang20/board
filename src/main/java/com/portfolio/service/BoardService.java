package com.portfolio.service;
import com.portfolio.domain.Board;
import com.portfolio.web.dto.BoardSearchCond;
import com.portfolio.web.dto.BoardUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;
public interface BoardService {
        Board save (Board board);

        void update(Long boardId, BoardUpdateDto updateParam);

        Optional<Board> findById(Long id);

        Page<Board> findBoards(BoardSearchCond boardSearchCond, Pageable pageable);

        void delete(Long boardId);

    }

