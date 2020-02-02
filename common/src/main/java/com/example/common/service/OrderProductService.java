package com.example.common.service;

import com.example.common.model.OrderProduct;

public interface OrderProductService {

    OrderProduct create(OrderProduct orderProduct);
    void deleteById(long id);
}
