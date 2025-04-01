package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

//정액할인제
public class FixDiscountPolicy implements DiscountPolicy {
    private int discoundFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discoundFixAmount;
        }
        return 0;
    }
}
