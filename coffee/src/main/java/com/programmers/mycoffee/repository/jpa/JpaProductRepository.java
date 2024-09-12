package com.programmers.mycoffee.repository.jpa;

import com.programmers.mycoffee.model.Category;
import com.programmers.mycoffee.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {

    Optional<List<ProductEntity>> findByCategory(Category category);

    Optional<ProductEntity> findByProductName(String productName);

}
