package com.anas.blogapi.services.impl;

import com.anas.blogapi.Mapper.Category_Mapper;
import com.anas.blogapi.entities.Category;
import com.anas.blogapi.exceptions.ResourceNotFoundException;
import com.anas.blogapi.Dto.Request.Category_Dto;
import com.anas.blogapi.respositories.CategoryRepo;
import com.anas.blogapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final Category_Mapper categoryMapper;
    @Override
    public Category_Dto CreateCategory(Category_Dto category_Dto) {
        Category category = this.categoryMapper.DtoToCategory(category_Dto);
        return this.categoryMapper.CategoryToDto(this.categoryRepo.save(category));
    }

    @Override
    public Category_Dto UpdateCategory(Category_Dto category_Dto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        category.setCategoryTitle(category_Dto.getCategoryTitle());
        category.setCategoryDescription(category_Dto.getCategoryDescription());
        return this.categoryMapper.CategoryToDto(this.categoryRepo.save(category));
    }

    @Override
    public Category_Dto GetCategoryById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        return this.categoryMapper.CategoryToDto(category);
    }

    @Override
    public List<Category_Dto> GetAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        return categories.stream().map(category -> this.categoryMapper.CategoryToDto(category)).collect(Collectors.toList());
    }

    @Override
    public void DeleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        this.categoryRepo.delete(category);

    }
}
