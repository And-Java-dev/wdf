package com.example.common.service.impl;

import com.example.common.model.Order;
import com.example.common.model.OrderStatus;
import com.example.common.model.Product;
import com.example.common.model.User;
import com.example.common.repository.OrderRepository;
import com.example.common.repository.ProductRepository;
import com.example.common.repository.UserRepository;
import com.example.common.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByDeadLine(LocalDateTime localDateTime) {
        return orderRepository.findAllByDeadline(localDateTime);
    }

    @Override
    public List<Order> findAllByDate(Date date) {
        return orderRepository.findAllByDate(date);
    }

    @Override
    public List<Order> findAllByProductsId(Product products_id) {
        return orderRepository.findAllByProductsId(products_id);
    }

    @Override
    public Order findByUserId(long id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public List<Order> findAllByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findAllByOrderStatus(orderStatus);
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void save(Order order, long user_id, List<Product> products) {
        LocalDateTime localDateTime = LocalDateTime.now();
        User user = userRepository.getOne(user_id);
        order = Order.builder()
                .date(new Date())
                .deadline(localDateTime.plusDays(10))
                .products(products)
                .user(user)
                .build();
        orderRepository.save(order);
    }

    @Override
    public void changeOrderStatus(OrderStatus orderStatus,long id) {
        Order one = orderRepository.getOne(id);
        one.setOrderStatus(orderStatus);
        orderRepository.save(one);
    }
}
