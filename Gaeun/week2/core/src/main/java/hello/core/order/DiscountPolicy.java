package hello.core.order;

import hello.core.member.Member;

public interface DiscountPolicy {
    /**
     * return시 각 등급별 얼만큼 할인해야하는지를 반환
     */
    int discount(Member member, int price);
}
