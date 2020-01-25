package com.example.admin.controller;

import com.example.admin.security.CurrentUser;
import com.example.common.model.Category;
import com.example.common.model.Product;
import com.example.common.service.CategoryService;
import com.example.common.service.ProductService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public CategoryController(ProductService productService,CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/productsByCategory")
    public String categories(ModelMap modelMap, @RequestParam("name") String name){
        modelMap.addAttribute("products",productService.findAllByCategory(name));
        return "categories";
    }

    @PostMapping(value = "/addCategory")
    public String addCategory(Category category, ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        modelMap.addAttribute("products",productService.findAll());
        categoryService.addCategory(category);
        return "adminProfile";
    }
}
