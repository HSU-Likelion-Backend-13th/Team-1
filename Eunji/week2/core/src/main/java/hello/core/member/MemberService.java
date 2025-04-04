package hello.core.member;

public interface MemberService {
    void join(Member member); //회원가입 메서드
    Member findMember(Long memberId); //멤버 반환
}
