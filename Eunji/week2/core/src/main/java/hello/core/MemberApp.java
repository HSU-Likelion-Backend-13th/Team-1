package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp { //테스트
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig(); -> 개발자가 직접 Appconfig를 사용해서 객체를 생성하고 의존성 주입
//        MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "lion", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember: " + findMember.getName());
        System.out.println("Member: "+ member.getName());
    }
}
