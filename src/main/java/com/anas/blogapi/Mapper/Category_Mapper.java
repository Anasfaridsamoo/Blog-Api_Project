package com.anas.blogapi.Mapper;

import com.anas.blogapi.entities.Category;
import com.anas.blogapi.Dto.Request.Category_Dto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Category_Mapper {
    Category_Dto CategoryToDto(Category category);
    Category DtoToCategory(Category_Dto category_Dto);
}
