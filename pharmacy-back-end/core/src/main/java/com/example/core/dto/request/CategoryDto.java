package com.example.core.dto.request;


import com.example.core.entity.Product;

import java.util.Date;
import java.util.List;

public class CategoryDto {
    private Integer catId;
    private String catName;
    private String catDescription;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private List<ProductDto> products;

    public CategoryDto(Integer catId, String catName, String catDescription, Date createdAt, String createdBy, Date updatedAt, String updatedBy, List<ProductDto> products) {
        this.catId = catId;
        this.catName = catName;
        this.catDescription = catDescription;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.products = products;
    }

    public CategoryDto() {

    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
