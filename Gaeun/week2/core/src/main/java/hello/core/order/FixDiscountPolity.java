package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolity implements DiscountPolicy {
    //고정 금액 할인
    private int discountFixAmount =1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }
        return 0;
    }
}
