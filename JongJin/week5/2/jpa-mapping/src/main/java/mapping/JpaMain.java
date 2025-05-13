package mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 멤버 생성
            Member member = new Member("종진", "노원구", "상계동", "한글비석로");
            em.persist(member);

            // 아이템 생성
            Item item = new Item("카레", 7900, 3);
            em.persist(item);

            // OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(2);
            orderItem.setOrderPrice(2*item.getPrice());
            orderItem.setItem(item);
            em.persist(orderItem);

            // Order
            Order order = new Order();
            order.setMember(member);
            order.setOrderDate(LocalDateTime.now());
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.addOrderItem(orderItem);
            em.persist(order);


            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
