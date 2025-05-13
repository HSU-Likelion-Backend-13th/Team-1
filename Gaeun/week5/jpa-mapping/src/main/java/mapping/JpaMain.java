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
            //즉시 로딩
            //현재 DB에 있는 멤버를 모두 조회해야하기에 만일 DB에 3명의 멤버가 있다면 3번 쿼리 발생
            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
            /// /////////////////
//
//            Team team = new Team();
//            team.setName("Team 2");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("user");
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear(); //1차 캐시 초기화 -> 프록시 테스트 가능
//
//            Member findMember = em.find(Member.class, member.getId());
//            //em.find(찾을 클래스, 가져올 Id(기본키)) //find는 pk를 기반으로 엔티티를 찾는다.
//
//            //팀은 아직 프록시 상태
//            System.out.println("team 클래스 : "+findMember.getTeam().getClass());
//            //getClass() : 현재 참조중인 클래스명 -> 프록시 출력
//
//            //쿼리가 나갈 것
//            System.out.println("team 이름 : "+findMember.getTeam().getName());
//            //지연로딩 - 엔티티와 관계가 설정된 다른 엔티티를 실제로 사용할때만 DB에서 불러옴
//
//            //위의 코드는 관계가 설정된 다른 엔티티를 아직 사용하지 않은 상태. (이름만 불러옴)
//            //그러나 아래 코드는 관계가 설정된 다른 엔티티(Team객체)의 이름을 가져오기에 실제로 사용됨으로 퀘리가 발송
            /// /////////////////////////

//            Member member = em.find(Member.class, 1);
//            System.out.println("member name : " + member.getUsername());

            /// ////////////////////
//            Team team = new Team();
//            team.setName("Team 1");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("admin");
//            member.setTeam(team);
//            em.persist(member);

            //////////////////////////

//            Team team = new Team();
//            team.setName("Team 1");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("admin");
//            //연관관계 주인에게만 값 입력
//            member.setTeam(team);
//            em.persist(member);
//
//            Member member2 = new Member();
//            member2.setUsername("admin2");
//            member2.setTeam(team);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//
//            List<Member> members = findMember.getTeam().getMembers();
//            for(Member m : members) {
//                System.out.println("멤버 : "+m.getUsername());
//            }

            ////////////////////////

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
