package com.example.common.service.impl;

import com.example.common.model.MaterialCategory;
import com.example.common.repository.MaterialCategoryRepository;
import com.example.common.service.MaterialCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialCategoryServiceImpl implements MaterialCategoryService {

    private final MaterialCategoryRepository materialCategoryRepository;

    public MaterialCategoryServiceImpl(MaterialCategoryRepository materialCategoryRepository) {
        this.materialCategoryRepository = materialCategoryRepository;
    }


    @Override
    public List<MaterialCategory> findAll() {
        return materialCategoryRepository.findAll();
    }

    @Override
    public void addCategory(MaterialCategory materialCategory) {
        materialCategoryRepository.save(materialCategory);
    }
}
