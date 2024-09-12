package com.programmers.mycoffee.model.admin;

public record JoinDto(
        String username, String password, Role role
) {
}
