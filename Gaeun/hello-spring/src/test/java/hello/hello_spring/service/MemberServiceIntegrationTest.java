package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //DB 개념, 테스트 케이스에 넣으면 테스트가 끝나고 이전 상태로 롤백을 실행
class MemberServiceIntegrationTest {
    //통합 테스트

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member); //아이디 반환

        //then
        Member findMember = memberService.findOne(saveId).get(); //아이디를 이용해 해당 멤버 찾기
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //memberService.join(member2)를 실행하는데 IllegalStateException이 발생 해야한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //여기서 메시지가 동일한지 확인
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}