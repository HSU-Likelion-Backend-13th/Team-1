package hello.core.member;

import java.util.HashMap;
import java.util.Map;

//test용 메모리 저장소
public class MemoryMemberRepository implements MemberRepository {
    //동시성 이슈가 발생 가능하다.
    private static Map<Long, Member> store = new HashMap<>();

    //회원 저장
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    //회원 조회
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
