package com.example.common.service;

import com.example.common.model.MaterialCategory;

import java.util.List;

public interface MaterialCategoryService {

    List<MaterialCategory> findAll();
    void addCategory(MaterialCategory materialCategory);

    void deleteById(long id);
}
