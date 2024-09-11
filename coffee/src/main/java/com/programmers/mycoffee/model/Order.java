package com.programmers.mycoffee.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class Order {
    private final UUID orderId;
    private final Email email;
    private String address;
    private String postcode;
    private final List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, Email email, String address, String postcode, List<OrderItem> orderItems, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 공통된 필드 업데이트 메서드
    private void update(Runnable fieldUpdater) {
        fieldUpdater.run();
        this.updatedAt = LocalDateTime.now(); // updatedAt 갱신
    }

    // 필드 업데이트 메서드
    public void setAddress(String address) {
        update(() -> this.address = address);
    }

    public void setPostcode(String postcode) {
        update(() -> this.postcode = postcode);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        update(() -> this.orderStatus = orderStatus);
    }
}
