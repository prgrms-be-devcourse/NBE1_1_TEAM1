package com.programmers.mycoffee.service;

import com.programmers.mycoffee.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<Order> findAll();

    Order update(Order order);

    Order findById(UUID orderID);

    Order findByName(String productName);
}
