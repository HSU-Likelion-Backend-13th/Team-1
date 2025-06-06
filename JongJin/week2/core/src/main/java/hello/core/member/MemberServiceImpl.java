package hello.core.member;

public class MemberServiceImpl implements MemberService {
    // MemberServiceImpl은 MemberRepository 인터페이스를 의존
    // MemberRepository를 실제로 할당하는 부분이 구현체를 의존
    // -> 구체화와 추상화에 둘 다 의존하고 있음
    // -> OCP 위반, DIP 위반
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 문제 : "new MemoryMemberRepository()"
    // 실제 할당하는 부분이 구현체, 구현체를 의존
    // 만약 다른 DB로 바꾸면 저 부분도 변경해야 된다. -> 이것은 OCP 위반

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
