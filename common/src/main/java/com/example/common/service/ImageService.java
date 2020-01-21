package com.example.common.service;

import com.example.common.model.Image;
import com.example.common.model.Material;
import com.example.common.model.Product;

import java.util.List;

public interface ImageService {

    List<Image> findAllByProductId(Product prod_id);
    List<Image> findAllByMaterialId(Material material_id);

    byte[] getImage(String name);
}
