package com.example.common.repository;

import com.example.common.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByCategoryTitle(String title);
    List<Product> findAllByMaterialTitle(String title);
}
