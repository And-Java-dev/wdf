package com.example.common.service;

import com.example.common.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> findAll();
    List<Product> findAllByCategory(String title);
    List<Product> findAllByMaterials(String materials_title);
    Product findById(long id);
    void save(Product product);
    void save(Product product, MultipartFile [] multipartFile, Size size, List<Material> materials, long category_id) throws IOException;
    void deleteById(long id);
    Product createProduct(List<Long> images, double height, double width, List<Long> materials,
                          Product product, String answer, Category category,String title,String desc,int count);

}
