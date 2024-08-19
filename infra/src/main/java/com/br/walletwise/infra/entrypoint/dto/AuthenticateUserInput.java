package com.br.walletwise.infra.entrypoint.dto;

public record AuthenticateUserInput(String usernameOrEmail, String password) {
}
