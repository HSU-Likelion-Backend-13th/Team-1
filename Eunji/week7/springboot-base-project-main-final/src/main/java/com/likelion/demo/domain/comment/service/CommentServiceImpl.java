package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.entity.Comment;
import com.likelion.demo.domain.comment.exception.CommentInvalidPassword;
import com.likelion.demo.domain.comment.exception.CommentNotFoundException;
import com.likelion.demo.domain.comment.repository.CommentRepository;
import com.likelion.demo.domain.comment.web.dto.*;
import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public CreateCommentRes create(Long postId, CreateCommentReq createCommentReq) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        Comment comment = Comment.builder()
                .post(post)
                .content(createCommentReq.getComment())
                .username(createCommentReq.getUsername())
                .password(createCommentReq.getPassword())
                .build();

        Comment res = commentRepository.save(comment);
        return new CreateCommentRes(res.getId());
    }

    @Override
    public CommentSummaryRes getAllComment(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        return new CommentSummaryRes(
                commentRepository.findAllByPostId(postId).stream()
                        .map(c -> new CommentSummaryRes.CommentSummary(
                                c.getId(),
                                c.getContent(),
                                c.getUsername(),
                                c.getPassword(),
                                c.getCreatedAt()
                        ))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public CommentDetailRes getComment(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);

        if(!comment.getPost().getId().equals(postId)) {
            throw new CommentNotFoundException();
        }

        return new CommentDetailRes(
                        comment.getId(),
                        comment.getPost().getId(),
                        comment.getContent(),
                        comment.getUsername(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()
        );
    }

    @Override
    public CommentDetailRes modifyComment(Long postId, Long commentId, ModifyCommentReq modifyCommentReq) {
        
        // comment 찾기
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);

        // 비밀번호 일치 확인
        if(!comment.getPassword().equals(modifyCommentReq.getPassword())) {
            throw new CommentInvalidPassword();
        }

        // 반환
        return new CommentDetailRes(
                comment.getId(),
                comment.getPost().getId(),
                comment.getContent(),
                comment.getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
