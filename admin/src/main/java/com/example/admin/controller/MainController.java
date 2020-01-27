package com.example.admin.controller;

import com.example.admin.security.CurrentUser;
import com.example.common.model.Product;
import com.example.common.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class MainController {

    @Value("${image.upload.dir}")
    private String imageUploadDir;

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ImageService imageService;
    private final MaterialService materialService;
    private final MaterialCategoryService materialCategoryService;
    private final OrderService orderService;
    private final UserService userService;

    public MainController(ProductService productService, CategoryService categoryService, ImageService imageService, MaterialService materialService, MaterialCategoryService materialCategoryService, OrderService orderService, UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.imageService = imageService;
        this.materialService = materialService;
        this.materialCategoryService = materialCategoryService;
        this.orderService = orderService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String home(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
            List<Product> products = productService.findAll();
            modelMap.addAttribute("products",products);
        }
        modelMap.addAttribute("products",productService.findAll());
        modelMap.addAttribute("categories",categoryService.findAll());
        modelMap.addAttribute("users",userService.findAll());
        modelMap.addAttribute("orders",orderService.findAll());
        modelMap.addAttribute("images",imageService.findAll());
        modelMap.addAttribute("materialCats",materialCategoryService.findAll());
//        modelMap.addAttribute("materials",materialService.findAll());

        log.info("Home page was opened.");
        return "boxed-layout";
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("picUrl") String picUrl) throws IOException {
        return imageService.getImage(picUrl);
    }


    @GetMapping("/single")
    public String single(ModelMap modelMap,@RequestParam("id") int  id,@AuthenticationPrincipal CurrentUser currentUser){
        modelMap.addAttribute(productService.findById(id));
        modelMap.addAttribute("images",productService.findById(id).getImages());
        if (currentUser.getUser() != null) {
            modelMap.addAttribute("user", currentUser.getUser());
            List<Product> products = productService.findAllByUserId(currentUser.getUser().getId());
            modelMap.addAttribute("products",products);
        }
        return "single";
    }
}
