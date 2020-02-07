package com.example.admin.controller;

import com.example.common.model.Material;
import com.example.common.model.MaterialCategory;
import com.example.common.service.MaterialCategoryService;
import com.example.common.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/materials")
public class MaterialController {

    private final MaterialService materialService;
    private final MaterialCategoryService materialCategoryService;

    public MaterialController(MaterialService materialService, MaterialCategoryService materialCategoryService) {

        this.materialService = materialService;
        this.materialCategoryService = materialCategoryService;
    }


    //go material page
    @GetMapping("/single/material")
    public String singleOrder(ModelMap modelMap, @RequestParam("id") int id) {
        Material material = materialService.findById(id);
        List<MaterialCategory> materialCategories = materialCategoryService.findAll();
        modelMap.addAttribute("materialCategories", materialCategories);
        modelMap.addAttribute("material", material);
        modelMap.addAttribute("images", materialService.findById(id).getImages());

        return "singleMaterial";
    }

    //find all materials
    @GetMapping("/allMaterials")
    public String getAll(ModelMap modelMap) {
        List<Material> all = materialService.findAll();
        modelMap.addAttribute("all", all);
        return "data-table-material";
    }

    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam("id") long id) {
        materialService.deleteById(id);
        return "redirect:/singleMaterial";
    }

}
