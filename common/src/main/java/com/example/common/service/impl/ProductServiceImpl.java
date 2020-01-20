package com.example.common.service.impl;

import com.example.common.model.*;
import com.example.common.repository.*;
import com.example.common.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${image.upload.dir}")
    private String imageUploadDir;

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    private final CategoryRepository categoryRepository;

    private final SizeRepository sizeRepository;

    private final MaterialRepository materialRepository;

    public ProductServiceImpl(ProductRepository productRepository, ImageRepository imageRepository, CategoryRepository categoryRepository, SizeRepository sizeRepository, MaterialRepository materialRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.sizeRepository = sizeRepository;
        this.materialRepository = materialRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(String title) {
        return productRepository.findAllByCategoryTitle(title);
    }

    @Override
    public List<Product> findAllByMaterial(String title) {
        return productRepository.findAllByMaterialTitle(title);
    }

    @Override
    public Product findById(long id) {
        return productRepository.getOne(id);
    }

    @Override
    public void save(Product product, MultipartFile[] multipartFile, long size_id, long material_id, long category_id) throws IOException {
        Image image = null;
        List<Image> images = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            String picUrl = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File file1 = new File(imageUploadDir, picUrl);
            image = new Image();
            image.setName(picUrl);
            file.transferTo(file1);
            images.add(image);
            imageRepository.save(image);
        }

        Category category = categoryRepository.getOne(category_id);
        Material material = materialRepository.getOne(material_id);
        Size size = sizeRepository.getOne(size_id);
         product = Product.builder()
                .category(category)
                .size(size)
                .material(material)
                .images(images)
                .build();
        productRepository.save(product);

    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}
