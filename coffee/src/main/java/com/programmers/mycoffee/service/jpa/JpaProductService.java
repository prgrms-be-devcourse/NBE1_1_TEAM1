package com.programmers.mycoffee.service.jpa;

import com.programmers.mycoffee.model.Category;
import com.programmers.mycoffee.model.Product;
import com.programmers.mycoffee.model.entity.ProductEntity;
import com.programmers.mycoffee.repository.jpa.JpaProductRepository;
import com.programmers.mycoffee.service.ProductService;
import com.programmers.mycoffee.utils.ProductMapTo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JpaProductService implements ProductService {

    private final JpaProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(Category category) {
        Optional<List<ProductEntity>> byCategory = productRepository.findByCategory(category);
        byCategory.orElseThrow();
        return ProductMapTo.mapToProductDtoList(byCategory.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return ProductMapTo.mapToProductDtoList(productRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(UUID productId) {
        Optional<ProductEntity> byId = productRepository.findById(productId);
        byId.orElseThrow();
        return Optional.of(ProductMapTo.mapToProductDto(byId.get()));
    }

    @Override
    public Product createProduct(String productName, Category category, long price) {
        return null;
    }

    @Override
    public Product createProduct(String productName, Category category, long price, String description) {
        Optional<ProductEntity> byProductName = productRepository.findByProductName(productName);
        if (byProductName.isPresent()) {
            throw new IllegalStateException("Same product exists :: " + productName);
        }
        productRepository.save(ProductMapTo.mapToProductEntity(productName, category, price, description));
        return null;
    }

    @Override
    @Transactional
    public Product updateProduct(UUID productId, String productName, Category category, long price, String description) {
        Optional<ProductEntity> byId = productRepository.findById(productId);
        byId.orElseThrow();
        ProductEntity productEntity = byId.get();
        productEntity.updateProduct(productName, category, price, description);
        return ProductMapTo.mapToProductDto(productEntity);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(UUID.randomUUID());
    }
}
