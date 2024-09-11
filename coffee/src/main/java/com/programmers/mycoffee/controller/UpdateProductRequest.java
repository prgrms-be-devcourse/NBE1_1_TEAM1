package com.programmers.mycoffee.controller;

import com.programmers.mycoffee.model.Category;

import java.util.UUID;

public record UpdateProductRequest(
        UUID productId,
        String productName,
        Category category,
        long price,
        String description
) {
}
