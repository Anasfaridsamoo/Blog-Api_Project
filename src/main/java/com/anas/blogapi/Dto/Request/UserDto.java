package com.anas.blogapi.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @NotEmpty
    @Size(min = 4, message = "Username must be at least 4 characters")
    private String name;
    @Email(message = "Email address is not valid")
    private String email;
    @NotEmpty(message = "Password is required")
    private String password;
}
