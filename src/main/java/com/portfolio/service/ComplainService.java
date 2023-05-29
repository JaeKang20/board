package com.portfolio.service;



import com.portfolio.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
    public class ComplainService {

        private final ComplainRepository complainRepository;
        private final MemberRepository memberRepository;
        private final BoardRepository boardRepository;


    @Transactional
    public void createComplain(Long boardId) {
        // 현재 회원과 게시글을 찾습니다.
                Board reportedBoard = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board not found"));

        // 해당 게시글에 대한 신고가 이미 있는지 확인합니다.
        Complain complain = complainRepository.findByBoard(reportedBoard);

        // 신고가 이미 있는 경우, 횟수를 증가시킵니다.
        // 신고가 없는 경우, 새로운 신고를 생성하고 횟수를 1로 설정합니다.
        if (complain != null) {
            complain.setNumber(complain.getNumber() + 1);
        } else {
            complain = new Complain();
            complain.setBoard(reportedBoard);
            complain.setNumber(1);
        }

        // 신고를 저장합니다.
        complainRepository.save(complain);
    }
    public List<Complain> getTopTenComplain(){
        return complainRepository.findTop10ByOrderByNumberDesc();
    }
    }

