package com.br.walletwise.infra.entrypoint.dto;

public record CreateUserInput(String firstname, String lastname, String username, String email, String password) {
}

