package com.example.core.repository;

import com.example.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    boolean existsByBarcode(String barcode);
}
