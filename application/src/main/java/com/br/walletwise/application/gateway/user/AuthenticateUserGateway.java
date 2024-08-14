package com.br.walletwise.application.gateway.user;

public interface AuthenticateUserGateway {
    void authenticate(String username, String password);
}
