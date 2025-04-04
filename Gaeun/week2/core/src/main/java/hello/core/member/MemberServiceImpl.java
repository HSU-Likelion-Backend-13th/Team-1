package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //MemberServiceImpl이 MemberRepository 인터페이스에 의존하는 것은 맞지만 동시에 할당하는 부분에서 구현체에도 의존하고 있는 상황
    //나중에 구현체를 DB로 바꾸게 되었을때 해당 코드도 수정해야함 -> OCP 위반

    //MemberServiceImpl은 MemberRepository 인터페이스를 의존
    //MemberRepository를 실제로 할당하는 부분이 구현체에 의존 -> DIP 위반
    // -> 구체화와 추상화에 둘다 의존하고 있음
    // -> OCP 위반, DIP 위반
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    //회원 조회
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
        //멤버 반환
    }
}
