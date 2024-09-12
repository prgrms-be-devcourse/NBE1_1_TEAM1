package com.programmers.mycoffee.repository.jpa;

import com.programmers.mycoffee.model.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaOrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {
}
