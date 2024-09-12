package com.programmers.mycoffee.controller;

import com.programmers.mycoffee.model.Email;
import com.programmers.mycoffee.model.Order;
import com.programmers.mycoffee.model.Product;
import com.programmers.mycoffee.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String ordersPage(Model model) {
        var orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping("new-order")
    public String newOrderPage() {
        return "new-order";
    }

    @PostMapping("/orders")
    public String newOrder(CreateOrderRequest orderRequest) {
        orderService.createOrder(
                new Email(orderRequest.email()),
                orderRequest.address(),
                orderRequest.postcode(),
                orderRequest.orderItems()
        );
        return "redirect:/orders";
    }

//    @GetMapping("/orders/edit/{orderId}")
//    public String editOrderPage(@PathVariable UUID orderId, Model model) {
//        var order = orderService.findById(orderId);
//        return "order-update";
//    }
//
//    @PostMapping("/orders/update/{orderId}")
//    public String updateOrder(UpdateOrderRequest orderRequest) {
//        orderService.update()
//
//    }
}
