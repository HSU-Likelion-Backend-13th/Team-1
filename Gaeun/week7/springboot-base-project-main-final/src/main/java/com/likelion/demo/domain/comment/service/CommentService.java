package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.web.dto.*;
import jakarta.validation.Valid;

public interface CommentService {

    CreateCommentRes create(Long postId, @Valid CreateCommentReq createCommentReq);

    CommentSummeryRes getAllComment(Long postId);

    CommentDetailRes getComment(Long postId, Long commentId);

    CommentDetailRes modifyComment(Long postId, Long commentId, ModifyCommentReq modifyCommentReq);


}
