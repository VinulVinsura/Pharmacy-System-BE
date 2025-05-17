package com.example.core.service.impl;

import com.example.core.dto.request.ProductDto;
import com.example.core.dto.response.ApiResponse;
import com.example.core.entity.Product;
import com.example.core.repository.ProductRepo;
import com.example.core.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<ApiResponse> saveProduct(ProductDto productDto) {
        try {
            if(productRepo.existsById(productDto.getProductId())){
                //update method
                return updateProduct(productDto);
            }
            if(productRepo.existsByBarcode(productDto.getBarcode())){
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(
                        new ApiResponse(HttpStatus.ALREADY_REPORTED.value(), "This barcode all ready reported", null)
                );
            }

            Product product=new Product();
            product.setProdName(productDto.getProdName());
            product.setProdDescription(productDto.getProdDescription());
            product.setBarcode(productDto.getBarcode());
            product.setPrice(productDto.getPrice());
            product.setQty(productDto.getQty());
            product.setStockStatus(productDto.getStockStatus());
            product.setMarketPlaceStatus(productDto.getMarketPlaceStatus());
            product.setManufactDate(productDto.getManufactDate());
            product.setExpDate(productDto.getExpDate());
            product.setCreatedAt(new Date());
            product.setCreatedBy(productDto.getCreatedBy());
            product.setUpdateAt(new Date());
            product.setUpdateBy(productDto.getUpdateBy());

            Product save = productRepo.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse(HttpStatus.CREATED.value(), "Product add success", save)
            );


        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null)
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAllProduct() {
        try {
            List<Product> all = productRepo.findAll();
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new ApiResponse(HttpStatus.FOUND.value(), "",all)
            );

        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null)
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getProductById(Integer id) {
        try {
            Optional<Product> byId = productRepo.findById(id);
            if(byId.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse(HttpStatus.NOT_FOUND.value(), "Invalid ID",null)
                );
            }

            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new ApiResponse(HttpStatus.FOUND.value(), "FOUND",byId)
            );

        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null)
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteProduct(Integer prodId) {
        try {
            Optional<Product> byId = productRepo.findById(prodId);
            if(byId.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse(HttpStatus.NOT_FOUND.value(), "Invalid ID",null)
                );
            }

            productRepo.delete(byId.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(HttpStatus.OK.value(), byId.get().getProdName()+" Product delete success",null)
            );



        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null)
            );
        }
    }


    private ResponseEntity<ApiResponse> updateProduct(ProductDto productDto){
        try {
            Optional<Product> byId = productRepo.findById(productDto.getProductId());
            byId.get().setProdName(productDto.getProdName());
            byId.get().setProdDescription(productDto.getProdDescription());
            byId.get().setBarcode(productDto.getBarcode());
            byId.get().setPrice(productDto.getPrice());
            byId.get().setQty(productDto.getQty());
            byId.get().setStockStatus(productDto.getStockStatus());
            byId.get().setMarketPlaceStatus(productDto.getMarketPlaceStatus());
            byId.get().setManufactDate(productDto.getManufactDate());
            byId.get().setExpDate(productDto.getExpDate());
//            byId.get().setCreatedAt(new Date());
//            byId.get().setCreatedBy(productDto.getCreatedBy());
            byId.get().setUpdateAt(new Date());
            byId.get().setUpdateBy(productDto.getUpdateBy());

            Product updated = productRepo.save(byId.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(HttpStatus.OK.value(), "Product updated success",updated)
            );


        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null)
            );
        }
    }
}
