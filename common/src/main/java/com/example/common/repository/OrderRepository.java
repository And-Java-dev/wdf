package com.example.common.repository;

import com.example.common.model.Order;
import com.example.common.model.OrderStatus;
import com.example.common.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUserId(long id);
    List<Order> findAllByDate(Date date);
    List<Order> findAllByDeadline(LocalDateTime localDateTime);
    List<Order> findAllByProductsId(Product products_id);
    Order findByUserId(long id);
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
}
