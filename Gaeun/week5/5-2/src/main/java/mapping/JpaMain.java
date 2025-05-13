package mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //멤버 생성
            Member member = new Member("gaeun", "성북구", "한성대", "창열" );
            em.persist(member); //객체를 영속성 컨텍스트에 저장한 상태(영속)

            //아이템 생성
            Item item = new Item("도리토스", 1600, 5);
            em.persist(item);

            //order Item
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(3);
            orderItem.setOrderPrice(3*item.getPrice());
            orderItem.setItem(item);
            em.persist(orderItem);

            //order
            Order order = new Order();
            order.setMember(member);
            order.setOrderDate(LocalDateTime.now());
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.addOrderItem(orderItem);
            em.persist(order);

            tx.commit(); //내부적으로 flush()가 실행 되면서, DB에 반영.
        }catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
