package com.anas.blogapi.services;

import com.anas.blogapi.Dto.Request.Category_Dto;

public interface CategoryService {
    Category_Dto CreateCategory(Category_Dto category_Dto);
    Category_Dto UpdateCategory(Category_Dto category_Dto, Integer categoryId);
    Category_Dto GetCategoryById(Integer categoryId);
    java.util.List<Category_Dto> GetAllCategories();
    void DeleteCategory(Integer categoryId);
}
