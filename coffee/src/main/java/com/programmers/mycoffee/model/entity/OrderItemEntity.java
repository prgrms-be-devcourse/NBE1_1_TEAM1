package com.programmers.mycoffee.model.entity;

import com.programmers.mycoffee.model.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class
OrderItemEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderEntity_id")
    private OrderEntitiy orderEntitiy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Builder
    public OrderItemEntity(UUID id,Category category, Long price, Long quantity, LocalDateTime updatedAt, OrderEntitiy orderEntitiy, ProductEntity productEntity) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.updatedAt = updatedAt;
        this.orderEntitiy = orderEntitiy;
        this.productEntity = productEntity;
    }
}
