package com.br.walletwise.infra.service.user;

import com.br.walletwise.application.gateway.user.AuthenticateUserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUserGatewayImpl implements AuthenticateUserGateway {
    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticate(String username, String password) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}