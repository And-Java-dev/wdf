package com.example.common.service;

import com.example.common.dto.OrderProductDto;
import com.example.common.model.Order;
import com.example.common.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(long id);

    List<Order> findAllByDeadLine(LocalDateTime localDateTime);

    List<Order> findAllByDate(Date date);

    List<Order> getAllOrders();

    Order create(Order order);

    void update(Order order);



    List<Order> findAllByUserId(long id);

    List<Order> findAllByOrderStatus(OrderStatus orderStatus);

    void delete(long id);

    //    void save(Order order,long userId,List<Long> products);
    void changeOrderStatus(OrderStatus orderStatus, long id);

    void save(Order order);

    List<Integer> findOrdersByDeadLine();

    Double findAllPerformedOrdersPriceSum();

    int findCountByStatus();

    double findAllOrdersPriceSum();

    Double findAllPerformedOrdersPriceSumByDate(int days);

    List<Order> findAllByDeadlineDayOfMonth();

    void validateProductsExistence(List<OrderProductDto> orderProducts);
}
