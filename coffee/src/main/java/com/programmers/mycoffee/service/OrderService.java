package com.programmers.mycoffee.service;

import com.programmers.mycoffee.model.Email;
import com.programmers.mycoffee.model.Order;
import com.programmers.mycoffee.model.OrderItem;

import java.util.List;

public interface OrderService {
    Order createOrder(Email email, String address, String postcode, List<OrderItem> orderItems);
}
