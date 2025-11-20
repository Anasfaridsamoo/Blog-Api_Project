package com.anas.blogapi.Dto.Request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentReqDto {
    @NotEmpty
    private String content;
}
