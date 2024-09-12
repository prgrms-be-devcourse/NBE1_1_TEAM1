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
public class ProductEntity {

    @Id
    @Column(name = "product_id")
    private UUID id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private Long price;

    private String description;

    @Column(nullable = false)
    private LocalDateTime createAt;

    private LocalDateTime updateAt;
    @Builder
    public ProductEntity(UUID id, String productName, Category category, Long price, String description, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
    public void updateProduct(String productName, Category category, long price, String description) {
        this.productName = productName;
        this.updateAt = LocalDateTime.now();
        this.category = category;
        this.description = description;
        this.price = price;
    }
}
