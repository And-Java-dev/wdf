package com.example.common.service;

import com.example.common.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    List<Image> findAllByProductId(long prodId);
    List<Image> findAllByMaterialId(long materialId);
    List<Image> findAll();
    byte[] getImage(String name);
    void save(Image image);
    Image addImage(MultipartFile file) throws IOException;
    void deleteById(long id);
    Image findById(long id);
    List<Image> addImages(MultipartFile [] multipartFiles) throws IOException;
}
