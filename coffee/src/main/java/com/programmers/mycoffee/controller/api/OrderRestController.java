package com.programmers.mycoffee.controller.api;

import com.programmers.mycoffee.controller.CreateOrderRequest;
import com.programmers.mycoffee.model.Email;
import com.programmers.mycoffee.model.Order;
import com.programmers.mycoffee.model.OrderStatus;
import com.programmers.mycoffee.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(@Qualifier("defaultOrderService") OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/v1/orders")
    public Order createOrder(@RequestBody CreateOrderRequest orderRequest) {
        return orderService.createOrder(
                new Email(orderRequest.email()),
                orderRequest.address(),
                orderRequest.postcode(),
                orderRequest.orderItems()
        );
    }

    @GetMapping("/api/v1/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID orderId) {
        Order order = orderService.findById(orderId);
        if(order != null){
            return new ResponseEntity<>(order, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/v1/all")
    public ResponseEntity<List<Order>> showAll(){
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/api/v1/update/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable UUID orderId, @RequestBody CreateOrderRequest orderRequest) {
        Order existOrder = orderService.findById(orderId);
        if(existOrder == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existOrder.setAddress(orderRequest.address());
        existOrder.setPostcode(orderRequest.postcode());
        existOrder.setOrderStatus(OrderStatus.ACCEPTED);

        Order updatedOrder = orderService.update(existOrder);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @GetMapping("/api/v1/product")
    public ResponseEntity<Order> findByProductName(@RequestParam String productName) {
        Order order = orderService.findByName(productName);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

