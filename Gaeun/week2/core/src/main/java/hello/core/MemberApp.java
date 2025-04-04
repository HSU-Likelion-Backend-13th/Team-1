package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//테스트
public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();
        //스프링 컨테이너사용
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //스프링 빈 찾아서 사용
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L,"lion", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        //레포에 저장한 후 저장된 멤버 가져오기
        System.out.println("findMember : " + findMember.getName());
        //저장한 멤버가 누구였는지
        System.out.println("Member :" + member.getName());
    }
}
