package com.programmers.mycoffee.controller;

import com.programmers.mycoffee.model.OrderItem;

import java.util.List;

public record CreateOrderRequest(String email, String address, String postcode, List<OrderItem> orderItems) {
}
