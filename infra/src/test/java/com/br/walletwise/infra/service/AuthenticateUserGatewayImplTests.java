package com.br.walletwise.infra.service;

import com.br.walletwise.application.gateway.user.AuthenticateUserGateway;
import com.br.walletwise.infra.mocks.MocksFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthenticateUserGatewayImplTests {
    @Autowired
    private AuthenticateUserGateway authenticateUserGateway;
    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    @DisplayName("Should authenticate user")
    void shouldAuthenticateUser() {
        String username = "any_username";
        String password = "any_password";

        Authentication authentication = MocksFactory.authenticationFactory();

        when(this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)))
                .thenReturn(authentication);

        this.authenticateUserGateway.authenticate(username, password);

        verify(this.authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
