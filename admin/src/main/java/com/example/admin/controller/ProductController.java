package com.example.admin.controller;


import com.example.common.model.Category;
import com.example.common.model.Product;
import com.example.common.model.Size;
import com.example.common.service.CategoryService;
import com.example.common.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    //add product
    @PostMapping("/addProduct")
    public String addProduct(Product product, @RequestParam("image") MultipartFile[] multipartFile, @RequestParam("size") Size size,
                             @RequestParam("materials") List<Long> materials, @RequestParam("category_id") long category_id) throws IOException {
        productService.save(product, multipartFile, size, materials, category_id);

        return "redirect:/boxed-layout";

    }

    //find all products
    @GetMapping("/allProducts")
    public String findAll(ModelMap modelMap) {
        List<Product> all = productService.findAll();
        modelMap.addAttribute("all", all);
        return "data-table-product";
    }

    //find products by categoryName
    @GetMapping("/productsByCategory")
    public String findAllByCategory(ModelMap modelMap, @RequestParam("name") String name) {
        List<Product> allByCategory = productService.findAllByCategory(name);
        modelMap.addAttribute("allByCategory", allByCategory);
        return "redirect:/singleProduct";
    }

    //go product page
    @GetMapping("/single/product")
    public String singleProduct(ModelMap modelMap, @RequestParam("id") long id) {
        Product product = productService.findById(id);
        List<Category> allCategories = categoryService.findAll();
        modelMap.addAttribute("product", product);
        modelMap.addAttribute("all", allCategories);
        modelMap.addAttribute("images", productService.findById(id).getImages());
        return "singleProduct";
    }

    //delete product by id
    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam("id") long id) {
        productService.deleteById(id);
        return "redirect:/singleProduct";
    }
}
