package com.br.walletwise.usecase.user;

public interface AuthenticateUser {
    String authenticate(String usernameOrEmail, String password);
}