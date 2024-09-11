package com.programmers.mycoffee.service;

import com.programmers.mycoffee.model.Category;
import com.programmers.mycoffee.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductsByCategory(Category category);

    List<Product> getAllProducts();

    Product createProduct(String productName, Category category, long price);

    Product createProduct(String productName, Category category, long price, String description);

}
