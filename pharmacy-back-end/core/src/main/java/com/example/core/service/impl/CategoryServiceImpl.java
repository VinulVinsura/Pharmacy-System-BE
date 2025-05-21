package com.example.core.service.impl;

import com.example.core.dto.request.CategoryDto;
import com.example.core.dto.response.ApiResponse;
import com.example.core.entity.Category;
import com.example.core.entity.Product;
import com.example.core.repository.CategoryRepo;
import com.example.core.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper=modelMapper;
    }

    @Override
    public ResponseEntity<ApiResponse> saveCategory(CategoryDto categoryDto) {
        try {


            if(categoryRepo.existsById(categoryDto.getCatId())){
               return updateCategory(categoryDto);
            }

            Category category=new Category();
            category.setCatName(categoryDto.getCatName());
            category.setCatDescription(categoryDto.getCatDescription());
            category.setCreatedAt(new Date());
            category.setCreatedBy(categoryDto.getCreatedBy());
            category.setUpdatedAt(new Date());
            category.setUpdatedBy(categoryDto.getUpdatedBy());

            Category save = categoryRepo.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse(HttpStatus.CREATED.value(), "Category add success", save)
            );

        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null)
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAllCategory() {
        try {
            List<Category> all = categoryRepo.findAll();
            List<CategoryDto> map = modelMapper.map(all, new TypeToken<List<CategoryDto>>(){}.getType());

            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new ApiResponse(HttpStatus.FOUND.value(), "",map)
            );

        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null)
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getCategoryById(Integer catId) {
        try {
            Optional<Category> byId = categoryRepo.findById(catId);
            CategoryDto map = modelMapper.map(byId.get(), CategoryDto.class);
            if(byId.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse(HttpStatus.NOT_FOUND.value(), "Invalid ID",null)
                );
            }

            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new ApiResponse(HttpStatus.FOUND.value(), "FOUND",map)
            );

        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null)
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteCategory(Integer catId) {
        try {
            Optional<Category> byId = categoryRepo.findById(catId);
            if(byId.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse(HttpStatus.NOT_FOUND.value(), "Invalid ID",null)
                );
            }

            categoryRepo.delete(byId.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(HttpStatus.OK.value(), byId.get().getCatName()+" Product delete success",null)
            );



        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null)
            );
        }
    }

    private ResponseEntity<ApiResponse> updateCategory(CategoryDto categoryDto){
        try {
            Optional<Category> byId = categoryRepo.findById(categoryDto.getCatId());
            byId.get().setCatName(categoryDto.getCatName());
            byId.get().setCatDescription(categoryDto.getCatDescription());
            byId.get().setUpdatedBy(categoryDto.getUpdatedBy());
            byId.get().setUpdatedAt(new Date());

            Category updated = categoryRepo.save(byId.get());
            CategoryDto map = modelMapper.map(byId.get(), CategoryDto.class);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(HttpStatus.OK.value(), "Product updated success",map)
            );


        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null)
            );

        }
    }
}
