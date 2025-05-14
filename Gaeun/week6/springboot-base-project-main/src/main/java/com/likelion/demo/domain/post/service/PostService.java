package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.web.dto.CreatePostReq;
import com.likelion.demo.domain.post.web.dto.CreatePostRes;
import com.likelion.demo.domain.post.web.dto.PostDetailRes;

public interface PostService {
    //게시글 작성
    CreatePostRes createOne(CreatePostReq createPostReq);
    //게시글 단건 조회
    PostDetailRes getById(Long postId);
}
