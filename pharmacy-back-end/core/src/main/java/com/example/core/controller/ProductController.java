package com.example.core.controller;

import com.example.core.dto.request.ProductDto;
import com.example.core.dto.response.ApiResponse;
import com.example.core.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<ApiResponse> saveProduct(@RequestBody ProductDto productDto){
        return  productService.saveProduct(productDto);

    }
}
