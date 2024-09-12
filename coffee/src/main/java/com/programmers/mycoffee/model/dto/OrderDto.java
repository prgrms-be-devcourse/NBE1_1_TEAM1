package com.programmers.mycoffee.model.dto;

import com.programmers.mycoffee.model.Email;
import com.programmers.mycoffee.model.Order;
import com.programmers.mycoffee.model.OrderItem;
import com.programmers.mycoffee.model.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class OrderDto {
    private final UUID orderId;
    private final Email email;
    private final String address;
    private final String postcode;
    private final OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        this.orderId = order.getOrderId();
        this.email = order.getEmail();
        this.address = order.getAddress();
        this.postcode = order.getPostcode();
        this.orderStatus = order.getOrderStatus();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
        this.orderItems = order.getOrderItems().stream()
                .map(OrderItemDto::new)
                .collect(Collectors.toList());
    }

    @Getter
    public static class OrderItemDto {
        private final UUID productId;
        private final String productName;
        private final int quantity;

        public OrderItemDto(OrderItem orderItem) {
            this.productId = orderItem.productId();
            this.productName = getProductNameById(orderItem.productId());
            this.quantity = orderItem.quantity();
        }

        private String getProductNameById(UUID productId) {
            return "Sample Product Name"; // Replace with actual logic
        }
    }
}