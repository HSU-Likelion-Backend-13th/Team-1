package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMeberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMeberRepository memberRepository;

    @BeforeEach // 메서드가 실행이 되기 전에 어떤 동작을 하는 것
    public void beforeEach() {
        memberRepository = new MemoryMeberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 메서드가 실행이 끝날 때마다 어떤 동작을 하는 것
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(findMember.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*
        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e) {
            // 여기로 오면 정상
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
        }
*/

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}