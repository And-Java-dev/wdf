package com.example.wdfrest.endPoint;


import com.example.common.model.Order;
import com.example.common.model.OrderStatus;
import com.example.common.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequestMapping("/rest/orders")
@RestController
public class OrderEndPoint {

    private final OrderService orderService;

    public OrderEndPoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("{userId}")
    public ResponseEntity findByUserId(@PathVariable("userId") long id) {
        List<Order> byUserId =  orderService.findByUserId(id);
        return ResponseEntity.ok(byUserId);
    }

    @GetMapping("findByStatus")
    public List<Order> findByOrderStatus(@RequestParam("orderStatus") OrderStatus orderStatus) {
        return orderService.findAllByOrderStatus(orderStatus);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") long id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity changeOrderStatus(@RequestParam("status") OrderStatus orderStatus, @PathVariable("id") long id) {
        orderService.changeOrderStatus(orderStatus, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{userId}")
    public ResponseEntity save(@RequestBody Order order, @RequestParam List<Long> products, @PathVariable("userId") long userId) {
        orderService.save(order, userId, products);
        return ResponseEntity.ok().build();
    }

    @GetMapping("findByProduct")
    public List<Order> findAllByProductId(@RequestParam("productId") long prod_id) {
        return orderService.findAllByProductsId(prod_id);
    }

    @GetMapping("byDeadLine")
    public List<Order> findByDeadLine(@RequestParam("deadLine") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
        return orderService.findAllByDeadLine(localDateTime);
    }

    @GetMapping("byDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public List<Order> findAllByDate(@RequestParam("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date date) {
        List<Order> allByDate = orderService.findAllByDate(date);
        return allByDate;
    }

    @GetMapping("findById/{id}")
    public ResponseEntity findById(@PathVariable("id") long id){
        Order byId = orderService.findById(id);
        return ResponseEntity.ok(byId);
    }
}
