package com.anas.blogapi.Dto.Request;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Category_Dto {
    private int id;
    @NotEmpty
    private String categoryTitle;
    @NotEmpty
    private String categoryDescription;
}

