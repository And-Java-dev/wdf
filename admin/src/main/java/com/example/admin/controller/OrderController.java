package com.example.admin.controller;

import com.example.common.dto.OrderProductDto;
import com.example.common.model.Order;
import com.example.common.model.OrderProduct;
import com.example.common.model.OrderStatus;
import com.example.common.model.Product;
import com.example.common.service.OrderProductService;
import com.example.common.service.OrderService;
import com.example.common.service.ProductService;
import com.example.common.service.UserService;
import com.example.common.service.impl.OrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderProductService orderProductService;
    private final ProductService productService;
    private final UserService userService;

    public OrderController(OrderService orderService, OrderProductService orderProductService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.orderProductService = orderProductService;
        this.productService = productService;
        this.userService = userService;
    }

    //go order page
    @GetMapping("/single/order")
    public String singleMaterial(ModelMap modelMap, @RequestParam("id") int id) {
        Order order = orderService.findById(id);
        modelMap.addAttribute("order", order);
        return "singleOrder";
    }

    //add order
    @PostMapping("/add/order")
    public String addOrder(@ModelAttribute OrderServiceImpl.OrderForm form,@RequestParam("userId") long userId) {
        List<OrderProductDto> formDto = form.getProductOrders();
        orderService.validateProductsExistence(formDto);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = LocalTime.now();

        Order order = Order.builder()
                .time(localTime)
                .orderStatus(OrderStatus.NEW)
                .deadline(localDateTime.plusDays(10))
                .user(userService.findById(userId))
                .build();
        order = this.orderService.create(order);
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDto) {
            OrderProduct orderProduct = new OrderProduct();
            Product byId = productService.findById(dto.getProduct().getId());

            if (byId.getCount()<dto.getQuantity()){
                throw new NullPointerException();
            }

            orderProduct.setOrder(order);
            orderProduct.setProduct(byId);
            orderProduct.setQuantity(dto.getQuantity());
            orderProducts.add(orderProductService.create(orderProduct));
            byId.setCount(byId.getCount() - orderProduct.getQuantity());
            productService.save(byId);
        }

        double price = 0;
        for (OrderProduct orderProduct : orderProducts) {
            price += orderProduct.getTotalPrice();
        }
        order.setPrice(price);
        order.setOrderProductsSize(formDto.size());


        this.orderService.update(order);

//        String uri = ServletUriComponentsBuilder
//                .fromCurrentServletMapping()
//                .path("/orders/{id}")
//                .buildAndExpand(order.getId())
//                .toString();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Location", uri);

        return "redirect:/";
    }

    //find all orders
    @GetMapping("/allOrder")
    public String getAll(ModelMap modelMap) {
        List<Order> all = orderService.findAll();
        List<OrderStatus> orderStatuses = Arrays.asList(OrderStatus.NEW,OrderStatus.DENIED,OrderStatus.PERFORMED,OrderStatus.COMPLETED);
        modelMap.addAttribute("orderStatuses",orderStatuses);
        modelMap.addAttribute("all", all);
        return "data-table-order";
    }

    @GetMapping("/calendar")
    public String getAllOrders(ModelMap modelMap) {
        List<Order> orders = orderService.findAll();
        //orders deadLine days this month
        List<Integer> days = orderService.findOrdersByDeadLine();
        //count orders by NEW and COMPLETED status
        int byStatus = orderService.findCountByStatus();
        //all orders price
        double allOrdersPrice = orderService.findAllOrdersPriceSum();
        //orders by deadline this month
        List<Order> orderList = orderService.findAllByDeadlineDayOfMonth();
        modelMap.addAttribute("orderList",orderList);
        modelMap.addAttribute("days", days);
        modelMap.addAttribute("byStatus", byStatus);
        modelMap.addAttribute("allOrdersPrice", allOrdersPrice);
        modelMap.addAttribute("all", orders);
        return "calendar";
    }

    @PostMapping("/changeOrderStatus")
    public String changeOrderStatus(@RequestParam("orderStatus") OrderStatus orderStatus,@RequestParam("orderId") long orderId){
        Order byId = orderService.findById(orderId);
        byId.setOrderStatus(orderStatus);
        orderService.save(byId);
        return "redirect:/allOrder";

    }


}
