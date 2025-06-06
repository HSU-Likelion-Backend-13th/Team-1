package mapping;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 단순 식별자, 객체 간의 관계 X, DB의 외래키 개념을 코드에 드러냄
    // -> SQL 사고방식
    //@Column(name = "TEAM_ID")
    //private Long teamId;

    // 기본값 : fetch = FetchType.EAGER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
