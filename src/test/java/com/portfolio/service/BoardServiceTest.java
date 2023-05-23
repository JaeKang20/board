package com.portfolio.service;
import com.portfolio.domain.Board;
import com.portfolio.domain.BoardRepository;
import com.portfolio.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    private BoardService boardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        boardService = new BoardService(boardRepository);
    }

    @Test
    void saveBoard() {
        // Given
        Board board = createBoard();
        when(boardRepository.save(any(Board.class))).thenReturn(board);

        // When
        Board savedBoard = boardService.save(board);

        // Then
        assertThat(savedBoard).isEqualTo(board);
        verify(boardRepository, times(1)).save(any(Board.class));
    }

    private Board createBoard() {
        Member member = new Member();
        String title = "Sample Title";
        String content = "Sample Content";
        String registerDate = "2023-05-21";
        String updateDate = null;
        String deleteDate = null;
        int boardLike = 0;
        int boardViewCount = 0;
        return Board.builder()
                .member(member)
                .title(title)
                .content(content)
                .registerDate(registerDate)
                .updateDate(updateDate)
                .deleteDate(deleteDate)
                .boardLike(boardLike)
                .boardViewCount(boardViewCount)
                .build();
    }

    @Test
    void getBoards() {
        // Given
        Pageable pageable = Pageable.ofSize(5).withPage(0);
        Sort sort = Sort.by("registerDate").descending();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<Board> expectedPage = createBoardPage();
        when(boardRepository.findAll(pageable)).thenReturn(expectedPage);

        // When
        Page<Board> boards = boardService.getBoards(pageable);

        // Then
        assertThat(boards).isEqualTo(expectedPage);
        verify(boardRepository, times(1)).findAll(pageable);
    }
    private Page<Board> createBoardPage() {
        List<Board> boardList = new ArrayList<>();
        boardList.add(createBoard());

        return new PageImpl<>(boardList, Pageable.unpaged(), 1);
    }
}