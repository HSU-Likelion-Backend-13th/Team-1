package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.entity.PostState;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import com.likelion.demo.domain.post.web.dto.CreatePostReq;
import com.likelion.demo.domain.post.web.dto.CreatePostRes;
import com.likelion.demo.domain.post.web.dto.PostDetailRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public CreatePostRes createOne(CreatePostReq createPostReq) {
        //1. createPostReq가지고 Post Entity 생성
        Post post = Post.builder()
                .title(createPostReq.getTitle())
                .content(createPostReq.getContent())
                .username(createPostReq.getUsername())
                .password(createPostReq.getPassword())
                .state(PostState.PROGRESS)
                .build();

        //2. repository Post 저장 (postRepository 사용)
        Post savedPost = postRepository.save(post); //저장한 포스터의 엔티티를 넘김

        //3. CreatePostRes postId 반환
        return new CreatePostRes(savedPost.getId());//이거는 JPA덕분인건가?
    }

    @Override
    public PostDetailRes getById(Long postId) {
        //1. postId에 해당하는 Post - DB에서 조회
        Post post = postRepository.findById(postId)
                // 404 - postId에 대해 처리
                .orElseThrow(PostNotFoundException::new);

        //2. PostDetailRes 반환
        return new PostDetailRes(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUsername(),
                post.getPassword(),
                post.getState(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
