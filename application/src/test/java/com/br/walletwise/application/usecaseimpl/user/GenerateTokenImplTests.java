package com.br.walletwise.application.usecaseimpl.user;

import com.br.walletwise.application.gateway.GenerateTokenGateway;
import com.br.walletwise.application.usecasesimpl.user.GenerateTokenImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GenerateTokenImplTests {

    @Test
    @DisplayName("Should generate token")
    public void shouldGenerateToken() {
        GenerateTokenGateway generateTokenGateway = mock(GenerateTokenGateway.class);
        GenerateTokenImpl generateToken = new GenerateTokenImpl(generateTokenGateway);

        String username  =  "any_username";

        String accessToken = UUID.randomUUID().toString();

        when(generateTokenGateway.generate(username)).thenReturn(accessToken);

        String result = generateToken.generate(username);

        assertThat(result).isEqualTo(accessToken);
        verify(generateTokenGateway, times(1)).generate(username);
    }
}
