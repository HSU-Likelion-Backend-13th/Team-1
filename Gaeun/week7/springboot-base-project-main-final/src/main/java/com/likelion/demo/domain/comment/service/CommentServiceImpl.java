package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.entity.Comment;
import com.likelion.demo.domain.comment.exception.CommentInvalidException;
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
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        Comment comment = Comment.builder()
                .post(post)
                .content(createCommentReq.getContent())
                .username(createCommentReq.getUsername())
                .password(createCommentReq.getPassword())
                .build();

        Comment res = commentRepository.save(comment);
        return new CreateCommentRes(res.getId());
    }

    //전체 조회
    @Override
    public CommentSummeryRes getAllComment(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        return new CommentSummeryRes(
                commentRepository.findAllByPostId(postId).stream()
                        .map(c -> new CommentSummeryRes.CommentSummery(
                                c.getId(),
                                c.getContent(),
                                c.getUsername(),
                                c.getPassword(),
                                c.getCreatedAt()
                        ))
                        .collect(Collectors.toList())
        );
    }

    //단일 조회
    @Override
    public CommentDetailRes getComment(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if(!comment.getPost().getId().equals(postId)) {
            throw new PostNotFoundException();
        }

        return new CommentDetailRes(
                postId,
                comment.getId(),
                comment.getContent(),
                comment.getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    @Override
    public CommentDetailRes modifyComment(Long postId, Long commentId, ModifyCommentReq modifyCommentReq) {
        //1. 댓글 존재 확인
        Comment foundcomment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        //2.게시글에 댓글 존재 확인
        if(!foundcomment.getPost().getId().equals(postId)) {
            throw new PostNotFoundException();
        }

        //3.댓글 비번 확인
        if(!foundcomment.getPassword().equals(modifyCommentReq.getPassword())) {
            throw new CommentInvalidException();
        }

        //4.댓글 수정
        foundcomment.modify(modifyCommentReq.getContent());

        //반환
        return new CommentDetailRes(
                postId,
                foundcomment.getId(),
                foundcomment.getContent(),
                foundcomment.getUsername(),
                foundcomment.getCreatedAt(),
                foundcomment.getUpdatedAt()
        );
    }

    @Override
    public void deleteOn(Long postId, Long commentId, DeleteCommentReq deletecommentReq) {
        //1. 댓글 존재 확인
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        //2. 게시글에 댓글 존재 확인
        if(!comment.getPost().getId().equals(postId)){
            throw new PostNotFoundException();
        }

        //3. 비밀번호 검증
        if(!comment.getPassword().equals(deletecommentReq.getPassword())) {
            throw new CommentInvalidException();
        }

        //4.삭제
        commentRepository.delete(comment);
    }

}
