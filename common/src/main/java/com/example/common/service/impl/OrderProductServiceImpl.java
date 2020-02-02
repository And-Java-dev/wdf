package com.example.common.service.impl;

import com.example.common.model.OrderProduct;
import com.example.common.repository.OrderProductRepository;
import com.example.common.service.OrderProductService;
import org.springframework.stereotype.Service;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
      return   orderProductRepository.save(orderProduct);

    }

    @Override
    public void deleteById(long id) {
        orderProductRepository.deleteById(id);
    }
}
