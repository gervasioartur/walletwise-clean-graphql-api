package com.br.walletwise.infra.helpers;

import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.repository.SessionJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class ValidateTokenTests {
    @Autowired
    private ValidateToken validateToken;

    @MockBean
    private GetUsernameFromToken getUsernameFromToken;
    @MockBean
    private SessionJpaRepository repository;

    @Test
    @DisplayName("Should return false if username from token is different of username form user details")
    void shouldReturnFalseIfUsernameFromTokenIsDifferentOfUsernameFormUserDetails() {
        String token = MocksFactory.faker.lorem().word();
        UserDetails userDetails =  MocksFactory.userJpaEntityFactory();

        when(this.getUsernameFromToken.get(token)).thenReturn(MocksFactory.faker.name().username());

        boolean result = this.validateToken.isValid(token, userDetails);

        assertThat(result).isFalse();
        verify(this.getUsernameFromToken, times(1)).get(token);
    }

    @Test
    @DisplayName("Should return false if session does not exist on find by token")
    void shouldReturnFalseIfSessionDoesNotExistOnFindByToken() {
        String token = MocksFactory.faker.lorem().word();
        UserDetails userDetails =  MocksFactory.userJpaEntityFactory();

        when(this.getUsernameFromToken.get(token)).thenReturn(userDetails.getUsername());
        when(this.repository.findByToken(token)).thenReturn(Optional.empty());

        boolean result = this.validateToken.isValid(token, userDetails);

        assertThat(result).isFalse();
        verify(this.getUsernameFromToken, times(1)).get(token);
        verify(this.repository, times(1)).findByToken(token);
    }
}
