package com.example.admin.controller;

import com.example.common.model.Order;
import com.example.common.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //go order page
    @GetMapping("/single/order")
    public String singleMaterial(ModelMap modelMap, @RequestParam("id") int  id){
        Order order = orderService.findById(id);
        modelMap.addAttribute("order",order);
        return "singleOrder";
    }

    //add order
    @PostMapping("/add/order")
    public String addOrder(Order order, @RequestParam("userId") long userId, @RequestParam("products")List<Long> products){
        orderService.save(order,userId,products);
        return "redirect:/boxed-layout";
    }

    //find all orders
    @GetMapping("/allOrder")
    public String findAll(ModelMap modelMap){
        List<Order> all = orderService.findAll();
        modelMap.addAttribute("all",all);
        return "data-table-order";
    }

    @GetMapping("/calendar")
    public String findAllOrders(ModelMap modelMap){
        List<Order> orders = orderService.findAll();
        //orders days
        List<Integer> days = orderService.findOrdersByDeadLine();
        //count orders by NEW and COMPLETED status
        int byStatus = orderService.findByStatus();
        //all orders price
        double allOrdersPrice = orderService.findAllOrdersPrice();
        modelMap.addAttribute("days",days);
        modelMap.addAttribute("byStatus",byStatus);
        modelMap.addAttribute("allOrdersPrice",allOrdersPrice);
        modelMap.addAttribute("all",orders);
        return "calendar";
    }


}
