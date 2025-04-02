package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "gaeun", Grade.BASIC);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "Bok", 20000);
        System.out.println("order : " + order); //order.String()을 하지 않아도 괜찮은지?
        System.out.println("최종가격 : " + order.calculatePrice());
    }
}
