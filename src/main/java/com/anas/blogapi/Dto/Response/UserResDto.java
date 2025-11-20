package com.anas.blogapi.Dto.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserResDto {
    private int id;
    private String name;
    private String email;
}
