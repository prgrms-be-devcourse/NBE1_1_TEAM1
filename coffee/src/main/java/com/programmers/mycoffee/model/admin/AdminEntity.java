package com.programmers.mycoffee.model.admin;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "amdin")
@NoArgsConstructor
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "admin_id")
    private UUID adminId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    public AdminEntity(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
