package hello.core.member;

public class Member {
    private Long id; //회원 식별 고유 아이디
    private String name; //회원 이름
    //회원 등급
    private Grade grade;

    public Member(Long id, String name, Grade grade) { //생성자
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
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
