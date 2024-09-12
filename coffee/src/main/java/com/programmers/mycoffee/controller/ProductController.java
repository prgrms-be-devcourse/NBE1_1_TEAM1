package com.programmers.mycoffee.controller;

import com.programmers.mycoffee.service.DefaultProductService;
import com.programmers.mycoffee.service.jpa.JpaProductService;
import lombok.RequiredArgsConstructor;
import com.programmers.mycoffee.model.Category;
import com.programmers.mycoffee.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(@Qualifier("defaultProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String productsPage(Model model) {
        var products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("new-product")
    public String newProductPage() {
        return "new-product";
    }

    @PostMapping("/products")
    public String newProduct(CreateProductRequest createProductRequest) {
        productService.createProduct(
                createProductRequest.productName(),
                createProductRequest.category(),
                createProductRequest.price(),
                createProductRequest.description());
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{productId}")
    public String editProductPage(@PathVariable UUID productId, Model model) {
        var product = productService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        model.addAttribute("categories", Category.values()); // Assuming Category is an enum
        return "product-update";
    }

    @PostMapping("/products/update/{productId}")
    public String updateProduct(UpdateProductRequest updateProductRequest) {
        productService.updateProduct(
                updateProductRequest.productId(),
                updateProductRequest.productName(),
                updateProductRequest.category(),
                updateProductRequest.price(),
                updateProductRequest.description());
        return "redirect:/products";
    }

    @PostMapping("/products/{productId}")
    public String deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
        return "redirect:/products";
    }
}
