package com.example.common.repository;

import com.example.common.model.Order;
import com.example.common.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {

}
