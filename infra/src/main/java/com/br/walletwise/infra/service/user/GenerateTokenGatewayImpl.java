package com.br.walletwise.infra.service.user;

import com.br.walletwise.application.gateway.user.GenerateTokenGateway;
import com.br.walletwise.infra.helper.CreateToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GenerateTokenGatewayImpl implements GenerateTokenGateway {
    private final CreateToken createToken;

    @Override
    public String generate(String username) {
        Map<String, Object> claims = new HashMap<>();
        return this.createToken.create(claims, username);
    }
}
