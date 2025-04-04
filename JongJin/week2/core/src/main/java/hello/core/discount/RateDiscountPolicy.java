package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

// 정률 할인제
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountRate = 10;


    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountRate/100;

            // OrderServiceImpl에서 작성했던 의문 해소!
            // 정률 할인제에서는 물건 가격의 10%만큼 할인을 하기때문에 물건의 가격도 넘겨 받아야함.
            // price라는 매개변수 : 단일적으로 생각한다면 정액 할인제는 필요 X, 정률 할인제는 필요 O
            // 우리는 OCP를 준수하기 위해 보편적으로 코드를 작성해야한다.
        }
        return 0;
    }
}
