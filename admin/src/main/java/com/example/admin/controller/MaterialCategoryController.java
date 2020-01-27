package com.example.admin.controller;

import com.example.common.model.MaterialCategory;
import com.example.common.service.MaterialCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/admin/materialCategories")
@Controller
public class MaterialCategoryController {

    private final MaterialCategoryService materialCategoryService;

    public MaterialCategoryController(MaterialCategoryService materialCategoryService) {
        this.materialCategoryService = materialCategoryService;
    }


    @PostMapping("/addMaterialCategory")
    public String addMaterialCategory(MaterialCategory materialCategory) {
        materialCategoryService.addCategory(materialCategory);
        return "redirect:/boxed-layout";
    }

    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam("id") long id){
        materialCategoryService.deleteById(id);
        return "redirect:/boxed-layout";
    }
}
