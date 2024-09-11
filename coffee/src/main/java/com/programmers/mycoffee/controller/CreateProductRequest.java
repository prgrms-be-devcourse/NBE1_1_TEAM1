package com.programmers.mycoffee.controller;

import com.programmers.mycoffee.model.Category;

public record CreateProductRequest(String productName, Category category, long price, String description) {
}
