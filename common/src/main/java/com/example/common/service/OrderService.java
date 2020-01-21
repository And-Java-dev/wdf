package com.example.common.service;

import com.example.common.model.Order;
import com.example.common.model.OrderStatus;
import com.example.common.model.Product;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface OrderService {

    List<Order> findAll();
    List<Order> findAllByDeadLine(LocalDateTime localDateTime);
    List<Order> findAllByDate(Date date);
    List<Order> findAllByProductsId(Product products_id);
    Order findByUserId(long id);
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
    void delete(long id);
    void save(Order order,long user_id,List<Product> products);
    void changeOrderStatus(OrderStatus orderStatus,long id);
    void save(Order order);
}
