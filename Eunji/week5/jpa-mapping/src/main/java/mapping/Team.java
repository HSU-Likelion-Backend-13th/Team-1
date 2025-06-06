package mapping;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    @Column (name = "TEAM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    //가짜주인. 가짜매핑(읽기전용 거울 매핑) -> 주인 반대편
    @OneToMany(mappedBy = "team") //팀은 하나 멤버는 여러명, mappedBy: 멤버클래스의 팀과 연결되어있다.
    private List<Member> members = new ArrayList<>(); //new ArrayList<>()붙여야 NullPointException 안뜸(관례)

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
