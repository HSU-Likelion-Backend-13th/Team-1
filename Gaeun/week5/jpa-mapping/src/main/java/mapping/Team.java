package mapping;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "TEAM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    //양방향
    @OneToMany(mappedBy = "team") //가짜 매핑(읽기 전용 거울 매핑) -> 주인의 반대편
    //멤버 클래스의 team 필드와 연결되어있다.
    private List<Member> members = new ArrayList<>();
    //new ArrayList<>() 작성시, 리스트에 add 할떄 NullPointExcepton이 뜨지 않는다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
