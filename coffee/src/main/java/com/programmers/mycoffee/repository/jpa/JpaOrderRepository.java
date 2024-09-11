package com.programmers.mycoffee.repository.jpa;

import com.programmers.mycoffee.model.entity.OrderEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaOrderRepository extends JpaRepository<OrderEntitiy, UUID> {

    Optional<List<OrderEntitiy>> findByEmail(String email);

    Optional<OrderEntitiy> findByEmailAndAddressAndPostCode(String email, String Address, String postCode);

    void deleteByEmail(String email);


}
