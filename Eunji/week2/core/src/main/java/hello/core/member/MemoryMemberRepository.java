package hello.core.member;

import java.util.HashMap;
import java.util.Map;

//DB가 아니라 테스트용 메모리 저장소. 서버 종료시 없어짐
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
