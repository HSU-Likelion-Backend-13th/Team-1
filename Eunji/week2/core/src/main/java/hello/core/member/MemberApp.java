package hello.core.member;

public class MemberApp { //테스트
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "lion", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember: " + findMember.getName());
        System.out.println("Member: "+ member.getName());
    }
}
