package com.portfolio.service;

import com.portfolio.config.SessionConst;
import com.portfolio.domain.Board;
import com.portfolio.domain.BoardRepository;
import com.portfolio.domain.Member;
import com.portfolio.web.dto.BoardSearchCond;
import com.portfolio.web.dto.BoardUpdateDto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;


    public Board save(Board board) {

        board.setRegisterDate(LocalDateTime.now().toString());

        return boardRepository.save(board);
    }
    public Page<Board> getBoards(Pageable pageable) {
        Sort sort = Sort.by("registerDate").descending();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return boardRepository.findAll(pageable);
    }
    public Page<Board> findAllSortedByAdminStatus(Pageable pageable) {
        return boardRepository.findAllSortedByAdminStatus(pageable);
    }


        public void update(Long boardId, BoardUpdateDto updateParam) {
            Board findBoard = findById(boardId).orElseThrow();
            findBoard.setUpdateDate(LocalDateTime.now().toString());
            findBoard.setTitle(updateParam.getTitle());
            findBoard.setContent(updateParam.getContent());
            boardRepository.save(findBoard);
        }


    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public Page<Board> findBoards(BoardSearchCond boardSearchCond, Pageable pageable) {
        if (StringUtils.hasText(boardSearchCond.getTitle())) {
            // 제목으로 검색
            return boardRepository.findByTitleContaining(boardSearchCond.getTitle(), pageable);
        } else if (StringUtils.hasText(boardSearchCond.getContent())) {
            // 내용으로 검색
            return boardRepository.findByContentContaining(boardSearchCond.getContent(), pageable);
        } else if (StringUtils.hasText(boardSearchCond.getNickname())) {
            // 글쓴이로 검색
            return boardRepository.findByMember_Nickname(boardSearchCond.getNickname(), pageable);
        } else {
            // 조건 없이 전체 조회
            return boardRepository.findAll(pageable);
        }
    }


    @Transactional
    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없습니다. id=" + boardId));

        boardRepository.deleteById(boardId);
    }
    public boolean addLike(Long boardId, Member loginMember) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        if (isAlreadyLiked(loginMember, boardId)) {
            return false;
        }
        addLikedBoard(loginMember, boardId);
        board.setBoardLike(board.getBoardLike() + 1);
        boardRepository.save(board);
        return true;
    }
    public boolean isAlreadyLiked(Member loginMember, Long boardId) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        Set<Long> likedBoardIds = (Set<Long>) session.getAttribute(SessionConst.LIKED_BOARD_IDS);
        System.out.println("Liked Board Ids: " + likedBoardIds);
        if (likedBoardIds != null && likedBoardIds.contains(boardId)) {
            return true;
        }
        return false;
    }
    public void addLikedBoard(Member loginMember, Long boardId) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession(true);
        Set<Long> likedBoardIds = (Set<Long>) session.getAttribute(SessionConst.LIKED_BOARD_IDS);
        if (likedBoardIds == null) {
            likedBoardIds = new HashSet<>();
        }
        likedBoardIds.add(boardId);
        session.setAttribute(SessionConst.LIKED_BOARD_IDS, likedBoardIds);
    }

    public void increaseViewCount(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        board.setBoardViewCount(board.getBoardViewCount() + 1);
        boardRepository.save(board);
    }
    public void decreaseViewCount(Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        board.setBoardViewCount(board.getBoardViewCount() - 1);
    }

    public List<Board> getTopTenBoardsView() {
        return boardRepository.findTop10ByOrderByBoardViewCountDesc();
    }

    public List<Board> getTopTenBoardsLike() {
        return boardRepository.findTop10ByOrderByBoardLikeDesc();
    }


}