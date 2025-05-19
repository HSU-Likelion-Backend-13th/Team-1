package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);
//        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
//        OrderService orderService = ac.getBean("orderService", OrderServiceImpl.class);

        // 인터페이스 기반으로 주입받는 방식이 더 유연하고 Spring의 의존성 주입 원칙에 부합
        // MemberServiceImpl -> MemeberService, OrderServiceImpl -> OrderService
        // AppConfig에서 구체적인 구현체(MemberServiceImpl, MemoryMemberRepository, RateDiscountPolicy)를 생성하고,
        // 이를 Spring IoC 컨테이너에 등록
        // 다른 클래스에서는 "인터페이스 타입"을 사용하여 Spring이 관리하는 구현체를 주입받는 방식입니다!
        // 따라서 구현체보다는 "인터페이스"를 기준으로 빈을 주입하는 것이 스프링의 DI를 잘 활용하는 방식입니다.

        Long memberId = 1L;
        Member member = new Member(memberId, "hyejeong", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "MacBook", 30000);
        System.out.println("order : " + order);
        System.out.println("최종 가격 : " + order.calculatePrice());
    }
}
