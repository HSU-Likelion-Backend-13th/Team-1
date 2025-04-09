package hello.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HelloLombok {

    private String name;
    private int age;

    //getter, setter메소드 없이 사용가능
    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("hello");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
