package com.anas.blogapi.controllers;

import com.anas.blogapi.Dto.Request.PostDto;
import com.anas.blogapi.Dto.Response.PostResDto;
import com.anas.blogapi.payloads.ApiResponse;
import com.anas.blogapi.payloads.PageResponse;
import com.anas.blogapi.services.FileService;
import com.anas.blogapi.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileService fileService;

    @Value("${project.image}")
    private String path;
    @PreAuthorize("@postSecurity.isSelf(#userId,authentication)")
    @PostMapping("/users/{userId}/categories/{categoryId}")
    ResponseEntity<PostResDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostResDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
    //update post
    @PreAuthorize("@postSecurity.isPostOwner(#postId,authentication)")
    @PutMapping("/{postId}")
    ResponseEntity<PostResDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId){
        return ResponseEntity.ok(postService.updatePost(postDto, postId));
    }
    //GetPost
    @GetMapping("/public/{postId}")
    ResponseEntity<PostResDto> getPost(@PathVariable Integer postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }
    //getAllPosts
    @GetMapping("/public")
    ResponseEntity<PageResponse<PostResDto>> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                         @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
                                                         @RequestParam(value = "sortBy",defaultValue = "PostId",required = false) String sortBy
    ){
        PageResponse<PostResDto> postResponse = postService.getAllPosts(pageNumber, pageSize,sortBy);
        return ResponseEntity.ok(postResponse);
    }
    //delete post
    @PreAuthorize("@postSecurity.isPostOwner(#postId,authentication)")
    @DeleteMapping("/{postId}")
    ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("post Deleted",HttpStatus.OK), HttpStatus.OK);
    }
    //Search post
    @GetMapping("/public/search/{title}")
    ResponseEntity<List<PostResDto>> searchPostByTitle(@PathVariable String title){
        return ResponseEntity.ok(postService.searchPosts(title));
    }

    @GetMapping("/public/users/{userId}")
    ResponseEntity<List<PostResDto>> getpostByUser(@PathVariable Integer userId){
        List<PostResDto> postResDto = postService.getAllPostsByUser(userId);
        return new ResponseEntity<>(postResDto, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}")
    ResponseEntity<List<PostResDto>> getpostByCategory(@PathVariable Integer categoryId){
        List<PostResDto> postResDto = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postResDto, HttpStatus.OK);
    }
@PostMapping("/public/{postId}/image")
    ResponseEntity<PostResDto> uploadPostImage(
            @PathVariable Integer postId,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        String fileName = fileService.uploadImage(path,image);
        PostResDto updatedPost = postService.updatePostImage(postId, fileName);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping(value = "/public/image/{imageName}",produces = MediaType.IMAGE_PNG_VALUE)
    void getImage(@PathVariable ("imageName")String imageName, HttpServletResponse response) throws IOException {
        InputStream is = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(is, response.getOutputStream());

    }
}
