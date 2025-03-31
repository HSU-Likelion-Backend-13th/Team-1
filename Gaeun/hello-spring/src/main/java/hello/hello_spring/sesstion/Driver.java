package hello.hello_spring.sesstion;

 interface Car {
    public void drive();
}

public class Genesis implements Car {
    public void drive(){
        System.out.println("Genesis 운전");
    }
}

public class Dodge implements Car {
    public void drive(){
        System.out.println("Dodge 운전중 ");
    }
}


class Driver {
    void drive(Car car){
        car.drive();
    }
}
