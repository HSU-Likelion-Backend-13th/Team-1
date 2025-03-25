package hello.hello_spring.domain;

public class Member {

    private Long id; //데이터를 식별하기 위한 id 값(like 인덱스)
    private String name;


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
