package com.example.wdfrest.endPoint;


import com.example.common.dto.OrderProductDto;
import com.example.common.model.*;
import com.example.common.service.OrderProductService;
import com.example.common.service.OrderService;
import com.example.common.service.ProductService;
import com.example.common.service.impl.OrderServiceImpl;
import com.example.wdfrest.security.CurrentUser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/rest/orders")
@RestController
public class OrderEndPoint {

    private final OrderService orderService;
    private final OrderProductService orderProductService;
    private final ProductService productService;


    public OrderEndPoint(OrderService orderService, OrderProductService orderProductService, ProductService productService) {
        this.orderService = orderService;
        this.orderProductService = orderProductService;
        this.productService = productService;
    }

    private User getCurrentUser(Principal principal){
        User activeUser = new User();
        return   activeUser = (User) ((Authentication) principal).getPrincipal();
    }

//    @GetMapping
//    public List<Order> findAll() {
//        return orderService.findAll();
//    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderServiceImpl.OrderForm form,@AuthenticationPrincipal CurrentUser currentUser) {
        List<OrderProductDto> formDto = form.getProductOrders();
        orderService.validateProductsExistence(formDto);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = LocalTime.now();
        Order order = Order.builder()
                .time(localTime)
                .orderStatus(OrderStatus.NEW)
                .deadline(localDateTime.plusDays(10))
                .user(currentUser.getUser())
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

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    @GetMapping("findByUserId")
    public List<Order> getByUserId(@AuthenticationPrincipal CurrentUser currentUser) {
        List<Order> byUserId = orderService.findAllByUserId(currentUser.getUser().getId());
        return byUserId;
    }

    @GetMapping("findByStatus")
    public List<Order> getByOrderStatus(@RequestParam("orderStatus") OrderStatus orderStatus) {
        return orderService.findAllByOrderStatus(orderStatus);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") long id) {
        Order byId = orderService.findById(id);
        byId.setUser(null);
        byId.setOrderStatus(OrderStatus.DENIED);
        orderService.save(byId);
        return ResponseEntity.ok().build();
    }

//    @PutMapping("{id}")
//    public ResponseEntity changeOrderStatus(@RequestParam("status") OrderStatus orderStatus, @PathVariable("id") long id) {
//        orderService.changeOrderStatus(orderStatus, id);
//        return ResponseEntity.ok().build();
//    }

//    @PostMapping("save")
//    public ResponseEntity save(@RequestBody Order order, @RequestParam List<Long> products, @AuthenticationPrincipal CurrentUser currentUser) {
//        orderService.save(order, currentUser.getUser().getId(), products);
//        return ResponseEntity.ok().build();
//    }


    @GetMapping("byDeadLine")
    public List<Order> getByDeadLine(@RequestParam("deadLine") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
        return orderService.findAllByDeadLine(localDateTime);
    }

    @GetMapping("byDate")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public List<Order> getAllByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<Order> allByDate = orderService.findAllByDate(date);
        return allByDate;
    }

    @GetMapping("findById/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {
        Order byId = orderService.findById(id);
        return ResponseEntity.ok(byId);
    }
}
