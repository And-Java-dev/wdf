package com.example.common.repository;

import com.example.common.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MaterialRepository extends JpaRepository<Material,Long> {

    List<Material> findAllByCategoriesId(long categoriesId);
    List<Material> findAllByProductsId(long productsId);
    List<Material> findAllByMaterialCategoryTitle(String materialCategoryTitle);
}
