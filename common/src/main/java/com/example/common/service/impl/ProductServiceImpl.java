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


    public ProductServiceImpl(ProductRepository productRepository, ImageRepository imageRepository, CategoryRepository categoryRepository, SizeRepository sizeRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.sizeRepository = sizeRepository;

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
    public List<Product> findAllByMaterials(Material title) {
        return productRepository.findAllByMaterialsTitle(title);
    }

    @Override
    public Product findById(long id) {
        return productRepository.getOne(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void save(Product product, MultipartFile[] multipartFile, Size size, List<Material> materials, long category_id) throws IOException {
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

        sizeRepository.save(size);
        Category category = categoryRepository.getOne(category_id);
        product = Product.builder()
                .category(category)
                .size(size)
                .materials(materials)
                .images(images)
                .build();
        productRepository.save(product);

    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product createProduct(List<Image> images, double height, double width, int count, List<Material> materials, Product product,String answer) {
       double invoicePrice = 0;
        for (Material material : materials) {
            double invoicePrice1 = material.getInvoicePrice();
          invoicePrice = invoicePrice1++;
        }
        Size size = Size.builder()
                .height(height)
                .width(width)
                .build();
        double price = height * width * invoicePrice;
        product = Product.builder()
                .images(images)
                .materials(materials)
                .size(size)
                .price(price)
                .count(count)
                .build();
        if (answer.equals("yes")){
            productRepository.save(product);
        }
        return product;
    }
}
