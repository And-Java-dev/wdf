package com.example.common.service.impl;

import com.example.common.model.*;
import com.example.common.repository.*;
import com.example.common.service.MaterialService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Value("${image.upload.dir}")
    private String imageUploadDir;


    private final ImageRepository imageRepository;

    private final MaterialRepository materialRepository;

    public MaterialServiceImpl( ImageRepository imageRepository, MaterialRepository materialRepository) {
        this.imageRepository = imageRepository;
        this.materialRepository = materialRepository;
    }

    @Override
    public List<Material> findAllByCategoriesId(long categories_id) {
        return materialRepository.findAllByCategoriesId(categories_id);
    }

    @Override
    public List<Material> findAllByProductsId(long products_id) {
        return materialRepository.findAllByProductsId(products_id);
    }

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public List<Material> findAllByMaterialCategoryTitle(String materialCategory_title) {
        return materialRepository.findAllByMaterialCategoryTitle(materialCategory_title);
    }

    @Override
    public void save(Size size, MaterialCategory materialCategory, MultipartFile[] multipartFiles, List<Category> categories, Material material) throws IOException {
        Image image = null;
        List<Image> images = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            String picUrl = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File file1 = new File(imageUploadDir, picUrl);
            image = new Image();
            image.setName(picUrl);
            file.transferTo(file1);
            images.add(image);
            imageRepository.save(image);
        }
        material = Material.builder()
                .categories(categories)
                .images(images)
                .materialCategory(materialCategory)
                .size(size)
                .build();
        materialRepository.save(material);

    }

    @Override
    public void deleteById(long id) {
        materialRepository.deleteById(id);
    }

    @Override
    public Material findById(long id) {
        return materialRepository.getOne(id);
    }

    @Override
    public void save(Material material) {
        materialRepository.save(material);
    }

    @Override
    public List<Material> addMaterials(List<Long> materials) {
        List<Material> materialList = new ArrayList<>();
        for (Long material : materials) {
            Material byId = findById(material);
            materialList.add(byId);
        }

        return materialList;
    }


}
