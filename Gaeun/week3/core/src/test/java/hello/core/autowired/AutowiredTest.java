package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);}

    static class TestBean{
        //@Autowired(required=false) : 자동 주입할 재상이 없으면 수정자 메서드 자체가 호출 안됨
        //호출 안됨
        @Autowired(required = false)
        //기본값 : true로 생성시 생성자에 Member를 주입해야하는데 Member가 없는 상태로 오류가 발생
        public void setNoBean1(Member noBean1){
            System.out.println("setBean1 = " + noBean1);
        }

        //org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
        //null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("setBean2 = " + noBean2); //null이 입력
        }

        //Optional<> : 자동 주입할 대상이 없으면 Optional.empty가 입력된다
        //Optional.empty 호출
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("setBean3 = " + noBean3); //Optional.empty입력
        }
    }
}
