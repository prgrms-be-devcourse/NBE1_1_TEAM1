package com.programmers.mycoffee.service;

import com.programmers.mycoffee.model.Email;
import com.programmers.mycoffee.model.Order;
import com.programmers.mycoffee.model.OrderItem;
import com.programmers.mycoffee.model.OrderStatus;
import com.programmers.mycoffee.repository.OrderRepository;
import com.programmers.mycoffee.repository.jpa.JpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;


    public Order createOrder(Email email, String address, String postcode, List<OrderItem> orderItems) {
        Order order = new Order(
                UUID.randomUUID(),
                email,
                address,
                postcode,
                orderItems,
                OrderStatus.ACCEPTED,
                LocalDateTime.now(),
                LocalDateTime.now());
        return orderRepository.insert(order);
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public Order findById(UUID orderID) {
        return null;
    }

    @Override
    public Order findByName(String productName) {
        return null;
    }
}
