package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.web.dto.*;

public interface CommentService {

    CreateCommentRes create(Long postId, CreateCommentReq createCommentReq);

    CommentSummaryRes getAllComment(Long postId);

    CommentDetailRes getComment(Long postId, Long commentId);

    CommentDetailRes modifyComment(Long postId, Long commentId, ModifyCommentReq modifyCommentReq);
}
