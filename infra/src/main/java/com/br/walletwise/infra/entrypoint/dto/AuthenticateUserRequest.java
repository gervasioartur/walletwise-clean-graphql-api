package com.br.walletwise.infra.entrypoint.dto;

public record AuthenticateUserRequest(String usernameOrEmail, String password) {
}
