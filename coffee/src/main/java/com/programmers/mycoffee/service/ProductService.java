package com.programmers.mycoffee.service;

import com.programmers.mycoffee.model.Category;
import com.programmers.mycoffee.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    List<Product> getProductsByCategory(Category category);
    List<Product> getAllProducts();

    Product getProductById(UUID productId);

    Product createProduct(String productName, Category category, long price);
    Product createProduct(String productName, Category category, long price, String description);


    Product updateProduct(UUID productId, String productName, Category category, long price, String description);

    void deleteProduct(UUID productId);
}
