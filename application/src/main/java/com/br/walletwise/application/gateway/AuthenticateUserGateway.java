package com.br.walletwise.application.gateway;

public interface AuthenticateUserGateway {
    void authenticate(String username, String password);
}
