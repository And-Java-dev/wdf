package com.example.common.service;

import com.example.common.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MaterialService {

    List<Material> findAllByCategoriesId(long categoriesId);
    List<Material> findAllByProductsId(long productsId);
    List<Material> findAll();
    List<Material> findAllByMaterialCategoryTitle(String materialCategoryTitle);
    void  save(Size size , MaterialCategory materialCategory, MultipartFile [] multipartFiles,List<Category> categories,Material material) throws IOException;
    void  deleteById(long id);
    Material findById(long id);
    void  save(Material material);
    List<Material>getMaterials(List<Long> materials);
}
