package com.anas.blogapi.services;

import com.anas.blogapi.Dto.Request.PostDto;
import com.anas.blogapi.Dto.Response.PostResDto;
import com.anas.blogapi.payloads.PageResponse;

import java.util.List;

public interface PostService {
    PostResDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostResDto updatePost(PostDto postDto, Integer postId);

    PostResDto getPostById(Integer postId);

    PageResponse<PostResDto> getAllPosts(Integer pageNumber, Integer pageSize,String sortBy);

    void deletePost(Integer postId);

    List<PostResDto> getPostsByCategory(Integer categoryId);

    List<PostResDto> getAllPostsByUser(Integer UserId);

    List<PostResDto> searchPosts(String keyword);
    PostResDto updatePostImage(Integer postId, String fileName);
}
