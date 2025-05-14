package com.likelion.demo.domain.post.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyPostReq {
    private String title;
    private String content;
    private String password;
}
