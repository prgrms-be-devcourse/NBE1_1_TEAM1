package com.programmers.mycoffee.controller.api;

import com.programmers.mycoffee.controller.CreateOrderRequest;
import com.programmers.mycoffee.model.Email;
import com.programmers.mycoffee.model.Order;
import com.programmers.mycoffee.service.DefaultOrderService;
import com.programmers.mycoffee.service.jpa.JpaOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

//    private final DefaultOrderService orderService;

    private final JpaOrderService jpaOrderService;

    @PostMapping("/api/v1/orders")
    public Order createOrder(@RequestBody CreateOrderRequest orderRequest) {
//        return orderService.createOrder(
//                new Email(orderRequest.email()),
//                orderRequest.address(),
//                orderRequest.postcode(),
//                orderRequest.orderItems()
//        );
        return jpaOrderService.createOrder(
                new Email(orderRequest.email()),
                orderRequest.address(),
                orderRequest.postcode(),
                orderRequest.orderItems()
        );
    }
}
