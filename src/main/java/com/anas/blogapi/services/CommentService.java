package com.anas.blogapi.services;

import com.anas.blogapi.Dto.Request.CommentReqDto;
import com.anas.blogapi.Dto.Response.CommentResDto;

public interface CommentService {
    CommentResDto createComment(CommentReqDto commentReqDto, int postId, int userId);
    void deleteComment(int commentId);
}
