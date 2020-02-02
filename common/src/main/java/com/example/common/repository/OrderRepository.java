package com.example.common.repository;

import com.example.common.model.Order;
import com.example.common.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUserId(long userId);
    List<Order> findAllByDate(Date date);
    List<Order> findAllByDeadline(LocalDateTime localDateTime);
    List<Order> findByUserId(long id);
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
    int countAllByOrderStatus(OrderStatus orderStatus);
    @Query(value = "SELECT SUM(price) FROM orders", nativeQuery = true)
    Double findAllOrdersPriceSum();
    @Query(value = "SELECT SUM(price) FROM orders WHERE orders.order_status='PERFORMED'", nativeQuery = true)
    Double findAllPerformedOrdersPriceSum();
    @Query(value = "SELECT SUM(price) FROM orders WHERE orders.order_status='PERFORMED' AND orders.date > DATE_SUB(CURRENT_TIMESTAMP, INTERVAL :days DAY);", nativeQuery = true)
    Double findAllPerformedOrdersPriceSumByDate(@Param("days")int days);
    @Query(value = "Select * from orders Where deadline >= DATE_ADD(CURDATE(), INTERVAL -30 DAY) and deadline <= DATE_ADD(CURDATE(), INTERVAL 30 DAY)", nativeQuery = true)
    List<Order> findAllByDeadlineDayOfMonth();


}
