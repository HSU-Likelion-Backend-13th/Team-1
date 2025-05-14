package com.likelion.demo.domain.post.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CreatePostReq {
    @NotBlank(message = "게시글 제목은 비어있을 수 없습니다.") //null, "" 빈칸 전부 안됨
    private String title;

    //@NotBlank(message = "게시글 제목은 비어있을 수 없습니다.")
    private String content;

    //@NotBlank(message = "게시글 제목은 비어있을 수 없습니다.")
    private String username;

    //@NotBlank(message = "게시글 제목은 비어있을 수 없습니다.")
    private String password;
}
