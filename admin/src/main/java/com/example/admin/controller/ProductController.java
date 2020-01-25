package com.example.admin.controller;


import com.example.admin.security.CurrentUser;
import com.example.common.model.Product;
import com.example.common.model.Size;
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
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/addProduct")
    public String addProduct(Product product,@RequestParam("image") MultipartFile[] multipartFile,@RequestParam("size") Size size,
                          @RequestParam("materials")List<Long>materials,   @RequestParam("category_id") long category_id) throws IOException {
        productService.save(product,multipartFile,size,materials,category_id);

        return "redirect:/admin";

    }

    @GetMapping(value = "/allProducts")
    public String findAll(ModelMap modelMap){
        List<Product> all = productService.findAll();
        modelMap.addAttribute("all",all);
        return "redirect:/admin";
    }

    @GetMapping("/productsByCategory")
    public String findAllByCategory(ModelMap modelMap, @RequestParam("name") String name){
        modelMap.addAttribute("products",productService.findAllByCategory(name));
        return "redirect:/admin";
    }
}
