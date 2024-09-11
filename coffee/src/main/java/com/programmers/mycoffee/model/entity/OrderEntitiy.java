package com.programmers.mycoffee.model.entity;

import com.programmers.mycoffee.model.OrderStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class OrderEntitiy {

    @Id
    @Column(name = "orderEntity_id")
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String postCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "orderEntitiy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItemList = new ArrayList<>();

    @Builder
    public OrderEntitiy(UUID id, String email, String address, String postCode, OrderStatus orderStatus, LocalDateTime updateAt) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.postCode = postCode;
        this.orderStatus = orderStatus;
        this.updateAt = updateAt;
    }


    public void settingOrderItemList(OrderItemEntity orderItemList) {
        this.orderItemList.add(orderItemList);
    }

    public void changUserEmail(String newEmail) {
        this.email = newEmail;
        this.updateAt = LocalDateTime.now();
    }

    public void updateOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.updateAt = LocalDateTime.now();
    }
}
