package com.anas.blogapi.Dto.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
    @NotEmpty(message = "Post title must not be empty")
    @Size(min = 1,max = 25,message = "Post title must be between 1 and 25 characters")
    private String title;
    @NotEmpty(message = "Post content must not be empty")
    @Size(min = 1, max = 100,message = "Post content must be between 1 and 100 characters")
    private String postContent;
    private String postImage;
}
