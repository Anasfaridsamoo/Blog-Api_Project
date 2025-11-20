package com.anas.blogapi.Dto.Response;

import com.anas.blogapi.Dto.Request.Category_Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class PostResDto {
    private int postId;
    private String title;
    private String postContent;
    private String postImage;
    private LocalDate postDate;
    private Category_Dto category;
    private UserResDto user;
    private Set<CommentResDto> comment = new HashSet<>();
}
