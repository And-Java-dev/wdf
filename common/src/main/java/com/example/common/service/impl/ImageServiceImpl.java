package com.example.common.service.impl;

import com.example.common.model.Image;
import com.example.common.model.Material;
import com.example.common.model.Product;
import com.example.common.repository.ImageRepository;
import com.example.common.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {



    @Value("${image.upload.dir}")
    private String imageUploadDir;

    private final ImageRepository imageRepository;

    public ImageServiceImpl( ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @Override
    public List<Image> findAllByProductId(long prodId) {
        return imageRepository.findAllByProductsId(prodId);
    }

    @Override
    public List<Image> findAllByMaterialId(long materialId) {
        return imageRepository.findAllByMaterialsId(materialId);
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public byte[] getImage(String name) {
        InputStream in = null;
        try {
            in = new FileInputStream(imageUploadDir + name);
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            log.error("Image does not exists", e);
        }
        return null;
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public Image addImage(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File uploadFile = new File(imageUploadDir, fileName);
        file.transferTo(uploadFile);
        Image image = Image.builder()
                .name(fileName)
                .build();
        imageRepository.save(image);
        return image;
    }

    @Override
    public void deleteById(long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Image findById(long id) {
        return imageRepository.getOne(id);
    }

    @Override
    public List<Image> addImages(MultipartFile [] multipartFiles) throws IOException {
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
        return images;
    }
}
