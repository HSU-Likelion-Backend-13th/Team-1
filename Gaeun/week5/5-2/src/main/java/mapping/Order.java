package mapping;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    //외래키 매핑
    //ORDERS 테이블의 Member_id 필드와 orders의 객체 member와 매핑
    private Member member;

    @OneToMany(mappedBy = "order") //연관관계의 주인을 알려줌
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //연관 관계 편의 메서드
    //왜 해당 클래스에서 추가하는 것일까?
    //연관관계 주인이 orderItem의 필드 인데 orderItem에서 추가해도 괜찮지 않을까?
    //@OneToMany는 읽기 전용이 아니였나????
    //-> 읽는 것은 DB만!!!!
    //DB에 관계 변화가 저장되는 것과 객체에 그 영향이 반영되는 것은 다른 것!
    //즉, 연관관게 주인이 관계 관련 DB 변화를 준다면, 그에 해댱되는 영향을 객체에 따로 넣어주어야한다.

    //ex) orderItem.set(order); 을 하면 DB에는 해당 연관관계가 저장된 상태
    //그러나 객체에서는 해당 관계가 설정되지 않았기에 컬렉션 orderItems에도 따로 추가하는 로직이 필요하다.
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }


    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
