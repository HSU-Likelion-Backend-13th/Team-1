package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //0,1,2 .. 키값 생성
    //동시성 문제 발생 가능성이 있다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //store.get(id) 값이 null이여도 Optional로 감싸서 반환
        //해당 id 가 있으면 store에서 해당 member 객체를 Optional로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                    .filter(member -> member.getName().equals(name))
                    .findAny();
                    //하나라도 찾는다. 못 찾으면 null이 Optional로 감싸서 전환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //Map의 member이 반환
    }

    public void clearStore() {
        store.clear();
    }
}
