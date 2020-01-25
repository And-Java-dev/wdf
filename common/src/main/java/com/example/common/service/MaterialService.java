package com.example.common.service;

import com.example.common.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MaterialService {

    List<Material> findAllByCategoriesId(long categories_id);
    List<Material> findAllByProductsId(long products_id);
    List<Material> findAll();
    List<Material> findAllByMaterialCategoryTitle(String materialCategory_title);
    void  save(Size size , MaterialCategory materialCategory, MultipartFile [] multipartFiles,List<Category> categories,Material material) throws IOException;
    void  deleteById(long id);
    Material findById(long id);
    void  save(Material material);
    List<Material>addMaterials(List<Long> materials);
}
