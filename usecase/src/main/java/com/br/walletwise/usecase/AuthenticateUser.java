package com.br.walletwise.usecase;

public interface AuthenticateUser {
    String authenticate(String usernameOrEmail, String password);
}