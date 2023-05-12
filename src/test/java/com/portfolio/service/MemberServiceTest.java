package com.portfolio.service;

import com.portfolio.domain.Member;
import com.portfolio.domain.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


public class MemberServiceTest {
    MemberService memberService;
    MemberRepository memberRepository;
    @BeforeEach
    public void beforeEach() {
        memberService = new MemberService(memberRepository);
    }
    /*@BeforeEach : 각 테스트 실행 전에 호출된다.
    테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고,
    의존관계도 새로 맺어준다.
  */

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
    }
    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 합니다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //예외 메세지가 예상한 메세지와 같아야합니다.
    }
}
