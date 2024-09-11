package com.programmers.mycoffee.controller;

import com.programmers.mycoffee.service.DefaultProductService;
import com.programmers.mycoffee.service.jpa.JpaProductService;
import lombok.RequiredArgsConstructor;
import com.programmers.mycoffee.model.Category;
import com.programmers.mycoffee.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final JpaProductService defaultProductService;

    @GetMapping("/products")
    public String productsPage(Model model) {
        var products = defaultProductService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("new-product")
    public String newProductPage() {
        return "new-product";
    }

    @PostMapping("/products")
    public String newProduct(CreateProductRequest createProductRequest) {
        defaultProductService.createProduct(
                createProductRequest.productName(),
                createProductRequest.category(),
                createProductRequest.price(),
                createProductRequest.description());
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{productId}")
    public String editProductPage(@PathVariable UUID productId, Model model) {
        var product = defaultProductService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        model.addAttribute("categories", Category.values()); // Assuming Category is an enum
        return "product-update";
    }

    @PostMapping("/products/update/{productId}")
    public String updateProduct(UpdateProductRequest updateProductRequest) {
        defaultProductService.updateProduct(
                updateProductRequest.productId(),
                updateProductRequest.productName(),
                updateProductRequest.category(),
                updateProductRequest.price(),
                updateProductRequest.description());
        return "redirect:/products";
    }

    @PostMapping("/products/{productId}")
    public String deleteProduct(@PathVariable UUID productId) {
        defaultProductService.deleteProduct(productId);
        return "redirect:/products";
    }
}
