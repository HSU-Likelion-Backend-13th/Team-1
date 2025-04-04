package hello.hello_spring.sesstion;

interface BoyFirend{
    void beBoyFirend();
}
interface Son{
    void beSon();
}

interface Employee{
    void beEmployee();
}

public class Man implements BoyFirend, Son{

    @Override
    public void beBoyFirend() {
        System.out.println("데이트중");
    }
    public void beSon() {
        System.out.println("부모님을 도우는중");
    }
    @Override
    public void be
}
