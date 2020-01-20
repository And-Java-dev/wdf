package com.example.common.service;

import com.example.common.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> findAll();
    List<Product> findAllByCategory(String title);
    List<Product> findAllByMaterial(String title);
    Product findById(long id);
    void save(Product product, MultipartFile [] multipartFile, long size_id,long material_id,long category_id) throws IOException;
    void deleteById(long id);

}
