package com.likelion.demo.domain.post.web.controller;

import com.likelion.demo.domain.post.service.PostService;
import com.likelion.demo.domain.post.web.dto.*;
import com.likelion.demo.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    // 의존성부여
    private final PostService postService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> CreatePost(@RequestBody @Valid CreatePostReq createPostReq) { // @Valid 유효성 검사
        // 서비스 계층 위임
        CreatePostRes createPostRes = postService.createOne(createPostReq);

        // 반환
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(createPostRes));
    }

    // 게시글 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<SuccessResponse<?>> getPostById(@PathVariable Long postId) {
        // 서비스 로직
        PostDetailRes postDetailRes = postService.getById(postId);

        // 반환
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(postDetailRes));
    }

    // 게시글 전체 조회
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllPosts() {
        // 서비스 로직
        PostSummaryRes postSummaryRes = postService.getAll();

        // 반환
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(postSummaryRes));
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<SuccessResponse<?>> modifyPost(
            @PathVariable Long postId,
            @RequestBody ModifyPostReq modifyPostReq
    ) {
        // 서비스
        PostDetailRes postDetailRes = postService.modifyOne(postId, modifyPostReq);

        // 반환
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(postDetailRes));
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<SuccessResponse<?>> deletePost(
            @PathVariable Long postId,
            @RequestBody DeletePostReq deletePostReq) {

        // 서비스 로직
        postService.deleteOne(postId, deletePostReq);
        // 반환
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.empty());
                // .body(SuccessResponse.ok(null)); 같은 의미
    }
}
