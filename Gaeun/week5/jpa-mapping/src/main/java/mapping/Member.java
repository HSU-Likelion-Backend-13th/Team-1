package mapping;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "MEMEBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //현재 상태는 단순 식별자
    //팀의 메서드나 속성에 접근 불가
    //객체 그래프 지향 탐색이 불가 (ex. member.getTeam().getName()이 불가)
    //sql적인 코드
//    @Column(name="TEAM_ID")
//    private Long teamId;

    //객체지향적 코드
    //멤버객체의 Team 참조와 멤버테이블의 Team_id 외래키를 매핑
    //연관관계 주인
    @ManyToOne
    @JoinColumn(name = "TEAM_ID") //연관관계 주인 -> 진짜 매핑
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
