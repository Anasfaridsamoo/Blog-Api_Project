package com.anas.blogapi.Mapper;

import com.anas.blogapi.Dto.Request.CommentReqDto;
import com.anas.blogapi.Dto.Response.CommentResDto;
import org.mapstruct.Mapper;
import com.anas.blogapi.entities.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentResDto commentToCommentResDto(Comment comment);
    Comment commentReqDtoToComment(CommentReqDto commentReqDto);
}
