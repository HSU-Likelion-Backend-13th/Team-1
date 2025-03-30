package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    // 사용자의 등급을 조회해서 사용자마다 다른 할인 정책을 적용해야 함
    // -> MemberRepository에 접근해서 사용자 등급을 조회해야 함

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // OCP 위반

    // 누군가가 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야 한다.
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // OrderService 입장에서는 할인에 대해서 몰라도, DiscountPolicy가 알아서 할인 금액을 계산하고 값을 던져줌
        // -> SRP (단일 책임 원칙) 준수!
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 2-(3) 의문점. dicountPrice는 FixDiscountPolicy로 가보면 고정 할인 금액(1,000)을 넘기고 있다.
        // itemPrice를 넘기는 의미가 없지 않나? 계산된 결과를 던지는 것이 아님.
        // 심지어 계산된 결과는 Order.calculatePrice()에서 함.


        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
