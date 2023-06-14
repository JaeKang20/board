package com.portfolio.web.board;

import com.portfolio.config.SessionConst;
import com.portfolio.config.argumentresolver.LoginUserAuthorize;
import com.portfolio.domain.*;
import com.portfolio.service.*;


import com.portfolio.web.dto.BoardUpdateDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Controller
@RequestMapping("/boards")
public class BoardApiController {

    private final BoardService boardService;



    @PostMapping(value = "/add", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> writeBoard(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                        @ModelAttribute Board board, RedirectAttributes redirectAttributes,
                                        @RequestBody Board requestBody) {
        String title = requestBody.getTitle();
        String content = requestBody.getContent();

        Board notSaveBoard = new Board();
        notSaveBoard.setTitle(title);
        notSaveBoard.setContent(content);
        notSaveBoard.setMember(loginMember);

        Board savedBoard = boardService.save(notSaveBoard);
        Long boardId = savedBoard.getBoardId();


        Map<String, Object> response = new HashMap<>();
        response.put("boardId", boardId);

        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/{boardId}/edit", consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> edit(@PathVariable Long boardId, @RequestBody BoardUpdateDto requestBody) {
        String title = requestBody.getTitle(); // 클라이언트로부터 받은 JSON 데이터에서 "title" 필드 값을 추출
        String content = requestBody.getContent(); // 클라이언트로부터 받은 JSON 데이터에서 "content" 필드 값을 추출

        BoardUpdateDto notUpdateBoard = new BoardUpdateDto();
        notUpdateBoard.setContent(content);
        notUpdateBoard.setTitle(title);
        boardService.update(boardId, notUpdateBoard);
        // 응답 데이터와 상태코드 등을 ResponseEntity 객체로 감싸서 반환
        Map<String, Object> response = new HashMap<>();
        response.put("boardId", boardId);
        return ResponseEntity.ok(response);
    }


    @PostMapping({"/{boardId}/boardLike"})
    public String addBoardLike(@PathVariable long boardId, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember
            ,RedirectAttributes redirectAttributes) {
        boolean isAlreadyLiked = boardService.addLike(boardId, loginMember);
        if (isAlreadyLiked) {
            redirectAttributes.addFlashAttribute("errorMessage", "이미 좋아요를 누르셨습니다.");
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "게시글을 좋아합니다!");
        }
        boardService.decreaseViewCount(boardId);
        return "redirect:/boards/{boardId}";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

        @PostMapping("/{boardId}")
    public String delete(@PathVariable Long boardId, @LoginUserAuthorize Member loginUser) {
        Board board = boardService.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Member writer = board.getMember();

        // 관리자 또는 게시물 작성자만이 해당 게시물을 삭제할 수 있도록 처리
        if (!loginUser.isAdmin() && !writer.getId().equals(loginUser.getId())) {
            throw new IllegalStateException("권한이 없습니다.");
        }

        boardService.delete(boardId);
        return "redirect:/boards"; // 삭제 후 리다이렉션할 URL을 정확히 지정

    }


}