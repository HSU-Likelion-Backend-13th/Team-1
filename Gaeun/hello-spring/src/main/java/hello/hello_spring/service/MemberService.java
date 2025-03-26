package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
    private final MemberRepository memberRepository;

    //@Autowired
    //외부에서 넣어주도록 생성자 생성
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//서비스에서는 비즈니스적 명칭으로 네이밍한다.
    /**
     *회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원x
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //내부에서 구현한 것보다는 메서드로 뽑아내는 것도 좋다.
    private void validateDuplicateMember(Member member) {
        //ifPresent : null이 아니라 값이 있다면 동작
        //Optional 값일때만 사용 가능
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     *전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     *아이디로 멤버 찾기
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
