package com.programmers.mycoffee.utils;


import com.programmers.mycoffee.model.Category;
import com.programmers.mycoffee.model.Product;
import com.programmers.mycoffee.model.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;

public class ProductMapTo {

    private static LocalDateTime timeCheck() {
        return LocalDateTime.now();
    }

    public static ProductEntity mapToProductEntity(String productName, Category category, long price, String description) {
        return ProductEntity.builder()
                .id(Utils.createUUID())
                .productName(productName)
                .description(description)
                .price(price)
                .category(category)
                .updateAt(timeCheck())
                .createAt(timeCheck())
                .build();
    }

    public static Product mapToProductDto(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getProductName(),
                productEntity.getCategory(),
                productEntity.getPrice(),
                productEntity.getDescription(),
                productEntity.getCreateAt(),
                productEntity.getUpdateAt()
        );
    }

    public static List<Product> mapToProductDtoList(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(ProductMapTo::converterToProductStream)
                .toList();
    }

    private static Product converterToProductStream(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getProductName(),
                productEntity.getCategory(),
                productEntity.getPrice(),
                productEntity.getDescription(),
                productEntity.getCreateAt(),
                productEntity.getUpdateAt()
        );

    }
}
