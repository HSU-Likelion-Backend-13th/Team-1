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
import org.hibernate.annotations.Comments;
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
                .content(createCommentReq.getContent())
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

        if (!comment.getPost().getId().equals(postId)) {
            throw new PostNotFoundException();
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
        // 댓글 확인, 404 - 댓글 없음
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if (!comment.getPost().getId().equals(postId)) {
            // 근데 이건 댓글을 추가할 때, postId가 있는지 검사하는데 굳이 필요한가?
            throw new PostNotFoundException();
        }

        // 비밀번호 검증, 403 - 비밀번호 불일치
        if (!comment.getPassword().equals(modifyCommentReq.getPassword())) {
            throw new CommentInvalidPassword();
        }

        // 댓글 수정
        comment.modify(modifyCommentReq.getContent());

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
