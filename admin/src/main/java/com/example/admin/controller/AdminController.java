package com.example.admin.controller;


import com.example.admin.security.CurrentUser;
import com.example.common.model.Product;
import com.example.common.model.User;
import com.example.common.service.CategoryService;
import com.example.common.service.EmailService;
import com.example.common.service.ProductService;
import com.example.common.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    private final ProductService productService;

    private final UserService userService;

    private final CategoryService categoryService;


    @Value("${image.upload.dir}")
    private String imageUploadDir;

    private final EmailService emailService;

    public AdminController(ProductService productService, EmailService emailService, UserService userService, CategoryService categoryService) {
        this.productService = productService;
        this.emailService = emailService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("/register")
    public String register(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if(currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute User user) {
        if (userService.isEmailExists(user.getEmail())) {
            userService.save(user);
            return "redirect:/";
        }
        return "redirect:/register";
    }

    @GetMapping("/activate")
    public String activate(@RequestParam("token") String token) {
        userService.activate(token);
        return "redirect:/";
    }




    @GetMapping("/profile")
    public String profile(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser/*@RequestParam("id") int id*/) {
        modelMap.addAttribute("user", currentUser.getUser());
        modelMap.addAttribute("categories", categoryService.findAll());
        List<Product> products = productService.findAllByUserId(currentUser.getUser().getId());
        modelMap.addAttribute("products",products);
//        modelMap.addAttribute("size", Size.values());
//        modelMap.addAttribute(productService.getOne(id));
        if (currentUser.getUser().getUserType().name().equals("ADMIN")){
            return "adminProfile";
        }
        return "userProfile";
    }



//    @GetMapping("/basket")
//    public String basket(ModelMap modelMap,@RequestParam("id") int  id,@AuthenticationPrincipal CurrentUser currentUser){
//        productService.addProductOnBasket(currentUser.getUser(),id);
//        List<Product> products = productService.findAllByUserId(currentUser.getUser().getId());
//        modelMap.addAttribute("products",products);
//        if (currentUser.getUser() != null) {
//            modelMap.addAttribute("user", currentUser.getUser());
//        }
//        return "basket";
//    }

    @GetMapping("/delete")
    public String deleteProducts(ModelMap modelMap,@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("id") int id) {
        modelMap.addAttribute("categories", categoryService.findAll());
        modelMap.addAttribute("products",productService.findAllByUserId(currentUser.getUser().getId()));
//        modelMap.addAttribute("size", Size.values());
        Product product = productService.findById(id);
        productService.deleteById(id);
        return "redirect:/profile";
    }

    @GetMapping("/search")
    public String search(ModelMap modelMap, @RequestParam String keyword,@AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("categories", categoryService.findAll());
//        modelMap.addAttribute("size", Size.values());
        List<Product> products = productService.findAllByName(keyword);
        modelMap.addAttribute("products", products);
        if (currentUser.getUser().getUserType().name().equals("ADMIN")){
            return "adminProfile";
        }
        return "userProfile";
    }
}
