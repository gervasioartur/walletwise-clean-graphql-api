package com.br.walletwise.application.gateway;

public interface AuthenticateUserGateway {
    String authenticate(String username, String password);
}
