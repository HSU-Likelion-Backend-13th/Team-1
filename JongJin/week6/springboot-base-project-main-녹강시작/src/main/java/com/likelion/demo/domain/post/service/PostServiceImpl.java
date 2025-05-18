package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.entity.PostState;
import com.likelion.demo.domain.post.exception.InvalidPasswordException;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import com.likelion.demo.domain.post.web.dto.*;
import com.likelion.demo.domain.post.web.dto.PostSummaryRes.PostSummary;
import com.likelion.demo.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    // 게시글 작성
    @Override
    public CreatePostRes createOne(CreatePostReq createPostReq) {
        // 1. createPostReq가지고 Post Entity 생성
        Post post = Post.builder()
                .title(createPostReq.getTitle())
                .content(createPostReq.getContent())
                .username((createPostReq.getUsername()))
                .password(createPostReq.getPassword())
                .state(PostState.PROGRESS)
                .build();

        // 2. repository Post 저장 (postRepository 사용)
        Post savedPost = postRepository.save(post);

        // 3. 반환 CreatePostRes
        return new CreatePostRes(savedPost.getId());
    }


    // 게시글 단건 조회
    @Override
    public PostDetailRes getById(Long postId) {
        // 1. postId에 해당하는 Post - DB에서 조회
        Post post = postRepository.findById(postId)
                // postId가 없으면 404
                .orElseThrow(PostNotFoundException::new);

        // 2. PostDetailRes 반환
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

    // 게시글 전체 조회
    @Override
    public PostSummaryRes getAll() {
        // 1. DB 에서 모든 Post 조회(postRepository)
        List<Post> posts = postRepository.findAll(); // 아무것도 없으면 빈 리스트 반환
        // 2. posts -> postSummaryRes 변환
        List<PostSummary> postSummaryList = new ArrayList<>();
        for (Post post : posts) {
            PostSummary postSummary = new PostSummary(
                    post.getId(),
                    post.getTitle(),
                    post.getUsername(),
                    post.getCreatedAt()
            );
            postSummaryList.add(postSummary);
        }

        // 3. 반환
        return new PostSummaryRes(postSummaryList);
    }

    // 게시글 수정
    @Transactional // jpa가 변경하는 것을 자동으로 감지해서 수정한 것을 DB에 업데이트 해줌
    @Override
    public PostDetailRes modifyOne(Long postId, ModifyPostReq modifyPostReq) {
        // 1. DB 에서 postId로 Post 찾기
        Post foundPost = postRepository.findById(postId)
                // 404 - 게시글 없음
                .orElseThrow(PostNotFoundException::new);

        // 2. 비밀번호 검증
        // 403 - 비밀번호 불일치
        if (!foundPost.getPassword().equals(modifyPostReq.getPassword())) {
            throw new InvalidPasswordException();
        }

        // 3. post 수정
        foundPost.modify(modifyPostReq.getTitle(), modifyPostReq.getContent());

        // PostDetailRes 반환
        return new PostDetailRes(
                foundPost.getId(),
                foundPost.getTitle(),
                foundPost.getContent(),
                foundPost.getUsername(),
                foundPost.getPassword(),
                foundPost.getState(),
                foundPost.getCreatedAt(),
                foundPost.getUpdatedAt()
        );
    }

    // 게시글 삭제
    @Transactional
    @Override
    public void deleteOne(Long postId, DeletePostReq deletePostReq) {
        // 1. 게시글 존재 확인
        // 404 - 게시글 존재하지 않음
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        // 2. 비밀번호 검증
        if (!post.getPassword().equals(deletePostReq.getPassword())) {
            // 403 - 비밀번호 불일치
            throw new InvalidPasswordException();
        }

        // 3. 삭제
        postRepository.delete(post);
    }


}
