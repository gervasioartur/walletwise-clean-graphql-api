package com.br.walletwise.infra.service.user;

import com.br.walletwise.application.gateway.user.EncodePasswordGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncodePasswordGatewayImpl implements EncodePasswordGateway {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return this.passwordEncoder.encode(password);
    }
}