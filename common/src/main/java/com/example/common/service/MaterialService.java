package com.example.common.service;

import com.example.common.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MaterialService {

    List<Material> findAllByCategoriesId(Category categories_id);
    List<Material> findAllByProductsId(Product products_id);
    List<Material> findAllByMaterialCategoryTitle(String materialCategory_title);
    void  save(Size size , MaterialCategory materialCategory, MultipartFile [] multipartFiles,List<Category> categories,Material material) throws IOException;
    void  deleteById(long id);
    Material findById(long id);
    void  save(Material material);
}
