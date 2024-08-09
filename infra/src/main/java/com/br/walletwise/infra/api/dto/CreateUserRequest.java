package com.br.walletwise.infra.api.dto;

public record CreateUserRequest(String firstname, String lastname, String username, String email, String password) {
}

