package com.likelion.demo.domain.comment.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCommentReq {
    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;
}
