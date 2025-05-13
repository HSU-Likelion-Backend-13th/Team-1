package mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Team team = new Team();
//            team.setName("Team 1");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("Member1");
//            member.setTeam(team);
//            em.persist(member);
//
//            Member member2 = new Member();
//            member2.setUsername("Member2");
//            member2.setTeam(team);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//
//            List<Member> members = findMember.getTeam().getMembers();
//            for(Member m : members) { //이터레이터
//                System.out.println("멤버: "+m.getUsername());
//            }
//            Team team = new Team();
//            team.setName("Team 2");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("Mds");
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear(); //1차캐시 초기화 -> 프록시 테스ㅡㅌ 가능
//
//            Member findMember = em.find(Member.class, member.getId());
//
//            // Team은 아직 프록시 상태
//            System.out.println("team 클래스 : " +findMember.getTeam().getClass());
//
//            // 여기서 쿼리 나감
//            System.out.println("team dlfma : " +findMember.getTeam().getName());

            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();


            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
