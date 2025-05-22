package com.example.core.controller;

import com.example.core.dto.request.CategoryDto;
import com.example.core.dto.response.ApiResponse;
import com.example.core.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add-category")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryDto categoryDto){
         return categoryService.saveCategory(categoryDto);
    }



    @GetMapping("/get-all-category")
    public ResponseEntity<ApiResponse> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/get-byId")
    public ResponseEntity<ApiResponse> getCategoryById(@RequestParam(name = "cat_id") Integer cat_id){
        return categoryService.getCategoryById(cat_id);
    }


    @DeleteMapping("/delete-category")
    public ResponseEntity<ApiResponse> deleteCategory(@RequestParam(name = "cat_id") Integer cat_id){
        return categoryService.deleteCategory(cat_id);

    }
}
