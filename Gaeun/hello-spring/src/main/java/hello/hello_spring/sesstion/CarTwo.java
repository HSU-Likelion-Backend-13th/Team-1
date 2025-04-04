package hello.hello_spring.sesstion;

public interface Tire {
    public void use();
}

public class SnowTire implements Tire {
    public void use() {
        System.out.println("Snow Tire사용중");
    }
}

class NormalTire implements Tire {
    public void use() {
        System.out.println("일반 타이어 사용중");
    }
}

public class CarTwo {
    private Tire tire;



}
