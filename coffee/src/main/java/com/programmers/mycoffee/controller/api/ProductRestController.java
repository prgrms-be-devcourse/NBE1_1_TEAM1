package com.programmers.mycoffee.controller.api;

import com.programmers.mycoffee.model.Category;
import com.programmers.mycoffee.model.Product;
import com.programmers.mycoffee.service.DefaultProductService;
import com.programmers.mycoffee.service.jpa.JpaProductService;
import com.programmers.mycoffee.service.jpa.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final DefaultProductService defaultProductService;

//    private final JpaProductService defaultProductService;

//    @GetMapping
//    public List<Product> productList(@RequestParam Optional<Category> category) {
//        return category
//                .map(defaultProductService::getProductsByCategory)
//                .orElse(defaultProductService.getAllProducts());
//    }

    @GetMapping
    public List<Product> jpaProductList(@RequestParam Optional<Category> category) {
        return defaultProductService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        // ProductRequest 객체를 Product 생성에 필요한 매개변수로 변환
        Product product = defaultProductService.createProduct(
                productRequest.getProductName(),
                productRequest.getCategory(),
                productRequest.getPrice(),
                productRequest.getDescription()
        );
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
