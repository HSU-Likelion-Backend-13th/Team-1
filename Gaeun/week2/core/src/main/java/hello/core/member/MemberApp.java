package hello.core.member;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L,"lion", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        //레포에 저장한 후 저장된 멤버 가져오기
        System.out.println("findMember : " + findMember.getName());
        //저장한 멤버가 누구였는지
        System.out.println("Member :" + member.getName());
    }
}
