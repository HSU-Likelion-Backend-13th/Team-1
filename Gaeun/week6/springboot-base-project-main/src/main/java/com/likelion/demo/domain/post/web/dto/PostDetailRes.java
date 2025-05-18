package com.likelion.demo.domain.post.web.dto;

import com.likelion.demo.domain.post.entity.PostState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record PostDetailRes(
        Long id,
        String title,
        String content,
        String username,
        String password,
        PostState state,
        LocalDateTime createdAt,
        LocalDateTime updateAt
){

}