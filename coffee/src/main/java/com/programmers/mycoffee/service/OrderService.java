package com.programmers.mycoffee.service;

import com.programmers.mycoffee.model.Email;
import com.programmers.mycoffee.model.Order;
import com.programmers.mycoffee.model.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order createOrder(Email email, String address, String postcode, List<OrderItem> orderItems);

    List<Order> findAll();

    Order update(Order order);

    Order findById(UUID orderID);

    Order findByName(String productName);
}
