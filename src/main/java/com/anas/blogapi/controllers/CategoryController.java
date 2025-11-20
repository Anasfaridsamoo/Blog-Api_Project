package com.anas.blogapi.controllers;

import com.anas.blogapi.payloads.ApiResponse;
import com.anas.blogapi.Dto.Request.Category_Dto;
import com.anas.blogapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/Categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/")
    ResponseEntity<Category_Dto> CreateCategory(@RequestBody Category_Dto category_Dto){
        Category_Dto categoryDto = categoryService.CreateCategory(category_Dto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }
    @PutMapping("/{categoryId}")
    ResponseEntity<Category_Dto> updateCategory(@RequestBody Category_Dto category_Dto, @PathVariable Integer categoryId){
        Category_Dto categoryDto = categoryService.UpdateCategory(category_Dto,categoryId);
        return ResponseEntity.ok(categoryDto);
    }
    @GetMapping("/")
    ResponseEntity<List<Category_Dto>> GetAllCategories(){
        List<Category_Dto> categoryDtoList = categoryService.GetAllCategories();
        return ResponseEntity.ok(categoryDtoList);
    }
    @GetMapping("/{categoryId}")
    ResponseEntity<Category_Dto> GetCategoryById(@PathVariable Integer categoryId){
        Category_Dto categoryDto = categoryService.GetCategoryById(categoryId);
        return ResponseEntity.ok(categoryDto);
    }
    @DeleteMapping("/{categoryId}")
    ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.DeleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Successful", HttpStatus.OK),HttpStatus.OK);
    }
}
