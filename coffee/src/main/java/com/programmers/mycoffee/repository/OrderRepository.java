package com.programmers.mycoffee.repository;

import com.programmers.mycoffee.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    Order insert(Order order);

    List<Order> findAll();

    Order update(Order order);

    Order findById(UUID orderId);

    Order findByName(String productName);
}
