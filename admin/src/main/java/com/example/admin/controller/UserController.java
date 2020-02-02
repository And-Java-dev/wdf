package com.example.admin.controller;


import com.example.admin.security.CurrentUser;
import com.example.common.model.Address;
import com.example.common.model.User;
import com.example.common.service.EmailService;
import com.example.common.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {


    private final UserService userService;


    @Value("${image.upload.dir}")
    private String imageUploadDir;

    private final EmailService emailService;

    public UserController(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;

    }

    @GetMapping("/register")
    public String register(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
        }
        return "form-elemnents";
    }


    @PostMapping("/register")
    public String registerPost(@ModelAttribute User user, @RequestParam("file") MultipartFile multipartFile,
                               @RequestParam("address") Address address) {
        if (userService.isEmailExists(user.getEmail())) {
            try {
                userService.register(user,multipartFile,address);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "redirect:/boxed-layout";
        }
        return "redirect:/form-elements";
    }

    @GetMapping("/activate")
    public String activate(@RequestParam("token") String token) {
        userService.activate(token);
        return "redirect:/boxed-layout";
    }


    //find all users
    @GetMapping("/allUsers")
    public String findAll(ModelMap modelMap) {
        List<User> all = userService.findAll();
        modelMap.addAttribute("all", all);
        return "data-table-user";
    }

    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam("id") long id) {
        userService.deleteById(id);
        return "redirect:/data-table-user";
    }
}
