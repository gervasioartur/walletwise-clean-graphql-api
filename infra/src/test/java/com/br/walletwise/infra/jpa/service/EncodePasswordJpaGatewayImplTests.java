package com.br.walletwise.infra.jpa.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class EncodePasswordJpaGatewayImplTests {
    @Autowired
    private EncodePasswordJpaGatewayImpl encodePasswordJpaGateway;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Should encode password")
    public void shouldEncodePassword() {
        String password = "password";
        String encodedPassword = "encodedPassword";

        when(this.passwordEncoder.encode(password)).thenReturn(encodedPassword);

        String result = encodePasswordJpaGateway.encode(password);

        assertThat(result).isEqualTo(encodedPassword);
    }

}
