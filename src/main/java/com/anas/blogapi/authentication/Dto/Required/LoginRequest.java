package com.anas.blogapi.authentication.Dto.Required;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequest {
    @Email(message = "Email address is not valid")
    private String email ;
    @NotEmpty(message = "Password is required")
    @Length(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;
}
