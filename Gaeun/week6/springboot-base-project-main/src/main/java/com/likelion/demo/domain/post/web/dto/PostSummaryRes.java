package com.likelion.demo.domain.post.web.dto;

import java.time.LocalDateTime;
import java.util.List;

//Res로 보내는 dto는 record 형태 추천
public record PostSummaryRes(
        List<PostSummary> postSummaryList
) {
    public record PostSummary(
            Long id,
            String title,
            String username,
            LocalDateTime createdAt
    ){}
}
