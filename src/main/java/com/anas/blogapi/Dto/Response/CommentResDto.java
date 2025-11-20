package com.anas.blogapi.Dto.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResDto {
    private int id;
    private String content;
    private UserResDto user;

}
