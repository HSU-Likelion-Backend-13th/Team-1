package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);
    //null처리 방법 보다는 Optional로 감싸서 처리
    Optional<Member> findByName(String name);

    //모든 멤버 값 반환
    List<Member> findAll();
}
