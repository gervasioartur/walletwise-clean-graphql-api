package com.br.walletwise.infra.service.user;

import com.br.walletwise.application.gateway.user.GenerateTokenGateway;
import com.br.walletwise.infra.helper.CreateToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GenerateTokenGatewayImplTests {
    @Autowired
    private GenerateTokenGateway generateTokenGateway;
    @MockBean
    private CreateToken createToken;


    @Test
    @DisplayName("Should return generated token")
    public void shouldReturnGeneratedToken() {
        String username = "any_username";
        String token = UUID.randomUUID().toString();
        Map<String, Object> claims = new HashMap<>();

        when(this.createToken.create(claims, username)).thenReturn(token);

        String result = this.generateTokenGateway.generate(username);

        assertThat(result).isEqualTo(token);
        verify(this.createToken).create(claims, username);
    }
}
