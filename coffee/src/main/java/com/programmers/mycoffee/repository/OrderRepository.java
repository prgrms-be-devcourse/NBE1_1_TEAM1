package com.programmers.mycoffee.repository;

import com.programmers.mycoffee.model.Order;

public interface OrderRepository {
    Order insert(Order order);
}
