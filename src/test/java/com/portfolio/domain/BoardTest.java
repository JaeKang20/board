package com.portfolio.domain;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoardTest {

    @Test
    void createBoard() {
        // Given
        Long boardId = 1L;
        Member member = new Member();
        String title = "Sample Title";
        String content = "Sample Content";
        String registerDate = "2023-05-21";
        String updateDate = null;
        String deleteDate = null;
        int boardLike = 0;
        int boardViewCount = 0;

        // When
        Board board = Board.builder()
                .boardId(boardId)
                .member(member)
                .title(title)
                .content(content)
                .registerDate(registerDate)
                .updateDate(updateDate)
                .deleteDate(deleteDate)
                .boardLike(boardLike)
                .boardViewCount(boardViewCount)
                .build();

        // Then
        assertThat(board.getBoardId()).isEqualTo(boardId);
        assertThat(board.getMember()).isEqualTo(member);
        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
        assertThat(board.getRegisterDate()).isEqualTo(registerDate);
        assertThat(board.getUpdateDate()).isEqualTo(updateDate);
        assertThat(board.getDeleteDate()).isEqualTo(deleteDate);
        assertThat(board.getBoardLike()).isEqualTo(boardLike);
        assertThat(board.getBoardViewCount()).isEqualTo(boardViewCount);
    }
}