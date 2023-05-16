package com.portfolio.web.board;

import com.portfolio.config.SessionConst;
import com.portfolio.domain.Board;
import com.portfolio.domain.Member;
import com.portfolio.service.BoardServiceImp;
import com.portfolio.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardApiController {

    private final BoardServiceImp boardServiceimp;
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

        Board savedBoard = boardServiceimp.save(notSaveBoard);
        Long boardId = savedBoard.getBoardId();


        Map<String, Object> response = new HashMap<>();
        response.put("boardId", boardId);

        return ResponseEntity.ok(response);
    }
}
