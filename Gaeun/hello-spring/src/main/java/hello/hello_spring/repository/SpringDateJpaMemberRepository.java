package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//spring data jpa가 레포지토리를 자동을 구현체로 만들어 스프린 빈으로 등록
//공통 기능이 이미 jpa내부에 구현되어 있다.
public interface SpringDateJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL select m from Member m where m.name =?
    //findByName과 같이 규칙을 이용해서 jpa가 구현체를 만들어 준다.
    @Override
    Optional<Member> findByName(String name);
}
