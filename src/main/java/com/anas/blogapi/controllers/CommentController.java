package com.anas.blogapi.controllers;

import com.anas.blogapi.Dto.Request.CommentReqDto;
import com.anas.blogapi.Dto.Response.CommentResDto;
import com.anas.blogapi.Mapper.CommentMapper;
import com.anas.blogapi.payloads.ApiResponse;
import com.anas.blogapi.services.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentService;
    @PreAuthorize("@userSecurity.isSelf(#userId,authentication)")
    @PostMapping("/post/{postId}/user/{userId}")
    ResponseEntity<CommentResDto> createComment(@RequestBody CommentReqDto commentReqDto, @PathVariable int postId, @PathVariable int userId) {
        CommentResDto createdComment = this.commentService.createComment(commentReqDto, postId, userId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
    @DeleteMapping("/admin/{commentId}")
    ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully", HttpStatus.OK), HttpStatus.OK);
    }
}
