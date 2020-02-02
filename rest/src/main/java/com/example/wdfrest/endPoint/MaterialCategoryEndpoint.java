package com.example.wdfrest.endPoint;

import com.example.common.model.MaterialCategory;
import com.example.common.service.MaterialCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/materialCategories/")
public class MaterialCategoryEndpoint {

    private final MaterialCategoryService materialCategoryService;

    public MaterialCategoryEndpoint(MaterialCategoryService materialCategoryService) {
        this.materialCategoryService = materialCategoryService;
    }

    @GetMapping
    public List<MaterialCategory> findAll(){
        return materialCategoryService.findAll();
    }
}
