package com.programmers.mycoffee.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
public class Product {
    private final UUID productId;
    private String productName;
    private Category category;
    private long price;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(UUID productId, String productName, Category category, long price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Product(UUID productId, String productName, Category category, long price, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private void update(Runnable fieldUpdater) {
        fieldUpdater.run();
        this.updatedAt = LocalDateTime.now();
    }

    public void setPrice(long price) {
        update(() -> this.price = price);
    }

    public void setCategory(Category category) {
        update(() -> this.category = category);
    }

    public void setProductName(String productName) {
        update(() -> this.productName = productName);
    }

    public void setDescription(String description) {
        update(() -> this.description = description);
    }

}
