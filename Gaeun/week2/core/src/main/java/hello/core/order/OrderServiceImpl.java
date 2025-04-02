package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolity;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    /**
     * 사용자의 등급을 조회해서 사용자마다 다른 할인 정책을 적용해야함
     *  -> MemberRepository에 접근해서 사용자 등급을 조회해야 함
     */
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolity();
    //구현체에도 의존한 상태이기에 할인 정책에서 기능 확장 또는 변경시 서비스 내의 코드에도 수정을 하여야한다. -> OCP 위반
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //누군가가 OderServiceImpl에 DiscountPolicy의 구현객체를 대신 생성하고 주입해주어야 한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //주문을 생성하는 메서드
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //사용자 등급을 조회하기 위해

        /**
         * 서비스 입장에서는 각 등급별 할인가격을 몰라도, DiscountPolicy가 알아서 할인금액을 계산하고 값을 던져준다.
         * -> SRP (단일 책임 원칙을 준수)
         */
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName,itemPrice, discountPrice);
    }
}
