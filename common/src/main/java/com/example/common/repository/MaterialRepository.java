package com.example.common.repository;

import com.example.common.model.Category;
import com.example.common.model.Material;
import com.example.common.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MaterialRepository extends JpaRepository<Material,Long> {

    List<Material> findAllByCategoriesId(Category categories_id);
    List<Material> findAllByProductsId(Product products_id);
    List<Material> findAllByMaterialCategoryTitle(String materialCategory_title);
}
