package com.anas.blogapi.services.impl;

import com.anas.blogapi.Dto.Request.CommentReqDto;
import com.anas.blogapi.Dto.Response.CommentResDto;
import com.anas.blogapi.Mapper.CommentMapper;
import com.anas.blogapi.entities.Comment;
import com.anas.blogapi.entities.Post;
import com.anas.blogapi.entities.User;
import com.anas.blogapi.exceptions.ResourceNotFoundException;
import com.anas.blogapi.respositories.CommentRepo;
import com.anas.blogapi.respositories.PostRepo;
import com.anas.blogapi.respositories.UserRepo;
import com.anas.blogapi.services.CommentService;
import com.anas.blogapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final CommentMapper commentMapper;
    @Override
    public CommentResDto createComment(CommentReqDto commentReqDto, int postId, int userId) {
        Comment comment = this.commentMapper.commentReqDtoToComment(commentReqDto);
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment = this.commentRepo.save(comment);
        return this.commentMapper.commentToCommentResDto(savedComment);
    }

    @Override
    public void deleteComment(int commentId) {
this.commentRepo.deleteById(commentId);
    }
}
