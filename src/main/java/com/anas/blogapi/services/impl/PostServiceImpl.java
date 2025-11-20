package com.anas.blogapi.services.impl;

import com.anas.blogapi.Dto.Request.PostDto;
import com.anas.blogapi.Dto.Response.PostResDto;
import com.anas.blogapi.Mapper.PostMapper;
import com.anas.blogapi.entities.Category;
import com.anas.blogapi.entities.Post;
import com.anas.blogapi.entities.User;
import com.anas.blogapi.exceptions.ResourceNotFoundException;
import com.anas.blogapi.payloads.PageResponse;
import com.anas.blogapi.respositories.CategoryRepo;
import com.anas.blogapi.respositories.PostRepo;
import com.anas.blogapi.respositories.UserRepo;
import com.anas.blogapi.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final PostRepo postRepo;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;

    @Override
    public PostResDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        Post post = this.postMapper.DtoReqToPost(postDto);
        post.setUser(user);
        post.setCategory(category);
        post.setPostDate(LocalDate.now());
        return this.postMapper.PostToResDto(this.postRepo.save(post));
    }

    @Override
    public PostResDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        post.setTitle(postDto.getTitle());
        post.setPostContent(postDto.getPostContent());
        post.setPostImage(postDto.getPostImage());
        return this.postMapper.PostToResDto(this.postRepo.save(post));
    }

    @Override
    public PostResDto getPostById(Integer postId) {
      Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        return this.postMapper.PostToResDto(post);
    }

    @Override
    public Page<PostResDto> getAllPosts(Integer pageNumber, Integer pageSize,String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Post> posts = this.postRepo.findAll(pageable);
        return postMapper.pageToResDto(posts);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostResDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        return posts.stream().map(post -> this.postMapper.PostToResDto(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostResDto> getAllPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        return posts.stream().map(post -> this.postMapper.PostToResDto(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostResDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        return posts.stream()
                .map(post -> this.postMapper.PostToResDto(post))
                .collect(Collectors.toList());
    }
    @Override
    public PostResDto updatePostImage(Integer postId, String fileName) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        post.setPostImage(fileName);
        Post updatedEntity = postRepo.save(post);
        return postMapper.PostToResDto(updatedEntity);
    }
}
