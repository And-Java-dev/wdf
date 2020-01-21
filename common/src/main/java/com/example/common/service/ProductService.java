package com.example.common.service;

import com.example.common.model.Image;
import com.example.common.model.Material;
import com.example.common.model.Product;
import com.example.common.model.Size;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> findAll();
    List<Product> findAllByCategory(String title);
    List<Product> findAllByMaterials(Material materials_title);
    Product findById(long id);
    void save(Product product);
    void save(Product product, MultipartFile [] multipartFile, Size size, List<Material> materials, long category_id) throws IOException;
    void deleteById(long id);
    Product createProduct(List<Image> images, double height,double width,int count, List<Material> materials, Product product,String answer);

}
