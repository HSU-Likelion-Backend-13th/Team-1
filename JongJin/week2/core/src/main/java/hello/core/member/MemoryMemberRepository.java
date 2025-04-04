package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    // 질문 : "빨간줄 왜 뜰까요?"
    // 내가 생각한 답변 : 인터페이스에서 만들었던 함수들을 구현해야 한다.

    private static Map<Long, Member> store = new HashMap<>();
    // 이것은 어떤 저장소인가? 테스트용 메모리 저장소
    // 서버를 종료하면 다 날아가버리는 휘발성 메모리 저장소

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

}
