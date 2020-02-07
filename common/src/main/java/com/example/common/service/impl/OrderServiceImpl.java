package com.example.common.service.impl;

import com.example.common.dto.OrderProductDto;
import com.example.common.exception.ResourceNotFoundException;
import com.example.common.model.Order;
import com.example.common.model.OrderStatus;
import com.example.common.repository.OrderProductRepository;
import com.example.common.repository.OrderRepository;
import com.example.common.repository.ProductRepository;
import com.example.common.repository.UserRepository;
import com.example.common.service.OrderService;
import com.example.common.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;



    private final ProductService productService;



    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;

    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(long id) {
        return orderRepository.getOne(id);
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
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
        order.setDate(new Date());
        return this.orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }

    @Override
    public List<Order> findAllByUserId(long id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public List<Order> findAllByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findAllByOrderStatus(orderStatus);
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }

//    @Override
//    public void save(Order order, long userId, List<Long> products) {
//        List<Product> productList = new ArrayList<>();
//        double total = 0;
//        for (Long product : products) {
//            Product one = productRepository.getOne(product);
//            productList.add(one);
//            double price = one.getPrice();
//            total += price;
//        }
//        LocalTime localTime = LocalTime.now();
//        LocalDateTime localDateTime = LocalDateTime.now();
//        User user = userRepository.getOne(userId);
//        order = Order.builder()
//                .date( new Date())
//                .deadline(localDateTime.plusDays(10))
//                .products(productList)
//                .time(localTime)
//                .user(user)
//                .price(total)
//                .orderStatus(OrderStatus.NEW)
//                .build();
//        orderRepository.save(order);
//    }

    @Override
    public void changeOrderStatus(OrderStatus orderStatus,long id) {
        Order one = orderRepository.getOne(id);
        one.setOrderStatus(orderStatus);
        orderRepository.save(one);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Integer> findOrdersByDeadLine(){
        List<Order> all = orderRepository.findAllByDeadlineDayOfMonth();
        List<Integer> days = new ArrayList<>();
        for (Order order : all) {
            int dayOfMonth = order.getDeadline().getDayOfMonth();
            days.add(dayOfMonth);
        }
        return days;
    }

    @Override
    public Double findAllPerformedOrdersPriceSum() {
        return orderRepository.findAllPerformedOrdersPriceSum();
    }

    @Override
    public int  findCountByStatus(){
        return orderRepository.countAllByOrderStatus(OrderStatus.NEW) + orderRepository.countAllByOrderStatus(OrderStatus.COMPLETED);
    }

    @Override
    public double findAllOrdersPriceSum() {
        return orderRepository.findAllOrdersPriceSum();
    }

    @Override
    public Double findAllPerformedOrdersPriceSumByDate(int days) {
        return orderRepository.findAllPerformedOrdersPriceSumByDate(days);
    }

    @Override
    public List<Order> findAllByDeadlineDayOfMonth() {
        return orderRepository.findAllByDeadlineDayOfMonth();
    }

    @Override
    public void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.findById(op
                        .getProduct()
                        .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    public static class OrderForm {

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDto> productOrders) {
            this.productOrders = productOrders;
        }
    }

}
