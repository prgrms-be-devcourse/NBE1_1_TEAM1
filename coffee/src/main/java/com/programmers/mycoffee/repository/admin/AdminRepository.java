package com.programmers.mycoffee.repository.admin;

import com.programmers.mycoffee.model.admin.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {
    Boolean existsByUsername(String username);

    AdminEntity findByUsername(String name);
}
