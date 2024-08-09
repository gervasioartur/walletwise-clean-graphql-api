package com.br.walletwise.infra.jpa.service;

import com.br.walletwise.application.gateway.EncodePasswordGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncodePasswordJpaGatewayImpl implements EncodePasswordGateway {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return this.passwordEncoder.encode(password);
    }
}