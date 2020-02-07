package com.example.admin.controller;

import com.example.admin.security.CurrentUser;
import com.example.common.model.Order;
import com.example.common.model.User;
import com.example.common.model.UserType;
import com.example.common.service.OrderService;
import com.example.common.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class MainController {


    private final OrderService orderService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public MainController(OrderService orderService, UserService userService, PasswordEncoder passwordEncoder) {

        this.orderService = orderService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    //go main paige boxed-layout
    @GetMapping("/")
    public String home(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            //all orders
            List<Order> orders = orderService.findAll();
            //orders by deadLine days this month
            List<Integer> days = orderService.findOrdersByDeadLine();
            //count orders by NEW and COMPLETED status
            int byStatus = orderService.findCountByStatus();
            //all orders price
            double allOrdersPrice = orderService.findAllOrdersPriceSum();
            //all PERFORMED orders price SUM
            double allPerformedOrdersPriceSum = orderService.findAllPerformedOrdersPriceSum();
            //orders by deadline this month
            List<Order> orderList = orderService.findAllByDeadlineDayOfMonth();
            modelMap.addAttribute("orderList",orderList);
            modelMap.addAttribute("allPerformedOrdersPriceSum",allPerformedOrdersPriceSum);
            modelMap.addAttribute("orders", orders);
            modelMap.addAttribute("days", days);
            modelMap.addAttribute("byStatus", byStatus);
            modelMap.addAttribute("allOrdersPrice", allOrdersPrice);
        }
        log.info("Home page was opened.");
        return "boxed-layout";
    }

    @PostMapping("/ordersSumByIntervalDays")
    public String getOrdersSumByDaysInterval(@RequestParam("days")int days){
        orderService.findAllPerformedOrdersPriceSumByDate(days);
        return "redirect:/";
    }

    //go user page
    @GetMapping("/profileUser")
    public String userProfile(ModelMap modelMap, @RequestParam("userId") long userId) {
        User user = userService.findById(userId);
        modelMap.addAttribute("user", user);
        return "profile-about";
    }

    //go admin page
    @GetMapping("/profileAdmin")
    public String adminProfile(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        if (currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
        }

        return "profile-admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
        User byEmail = userService.findByEmail(email);
        if (passwordEncoder.matches(password, byEmail.getPassword()) && byEmail.getUserType() == UserType.ADMIN) {
            return "boxed-layout";
        }
        return "login";
    }


}
