package com.example.common.repository;

import com.example.common.model.Image;
import com.example.common.model.Material;
import com.example.common.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByProductsId(Product products_id);
    List<Image> findAllByMaterialsId(Material materials_id);
}
