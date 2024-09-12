package com.programmers.mycoffee.model.admin;

public enum Role {
    ADMIN, USER;
    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.name().equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + role);
    }
}
