package com.likelion.demo.domain.post.entity;

import com.likelion.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor //모든 생성자 생성
public class Post extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String username;

    private String password;

    //수정일시, 작성일시 -> 상속

    @Enumerated(EnumType.STRING)
    private PostState state;

    //수정 로직 (제목, 내용)
    public void modify(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
