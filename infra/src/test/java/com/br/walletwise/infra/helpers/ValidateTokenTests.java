package com.br.walletwise.infra.helpers;

import com.br.walletwise.core.domain.model.GeneralEnumInt;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.SessionJpaEntity;
import com.br.walletwise.infra.persistence.repository.SessionJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
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

    @Test
    @DisplayName("Should return false if token has expired")
    void shouldReturnFalseIfTokenHasExpired() {
        String token = MocksFactory.faker.lorem().word();
        UserDetails userDetails =  MocksFactory.userJpaEntityFactory();

        SessionJpaEntity sessionEntity =  MocksFactory.sessionJpaEntityFactory();
        sessionEntity.setCreationDate(LocalDateTime.now().minusHours(GeneralEnumInt.JWT_TOKEN_EXPIRATION.getValue()));

        when(this.getUsernameFromToken.get(token)).thenReturn(userDetails.getUsername());
        when(this.repository.findByToken(token)).thenReturn(Optional.of(sessionEntity));

        sessionEntity.setActive(false);

        boolean result = this.validateToken.isValid(token, userDetails);

        assertThat(result).isFalse();
        verify(this.getUsernameFromToken, times(1)).get(token);
        verify(this.repository, times(1)).findByToken(token);
        verify(this.repository, times(1)).save(sessionEntity);
    }

    @Test
    @DisplayName("Should return true if token is valid")
    void shouldReturnTruIfTokenIsValid() {
        String token = MocksFactory.faker.lorem().word();
        UserDetails userDetails =  MocksFactory.userJpaEntityFactory();

        SessionJpaEntity sessionEntity =  MocksFactory.sessionJpaEntityFactory();

        when(this.getUsernameFromToken.get(token)).thenReturn(userDetails.getUsername());
        when(this.repository.findByToken(token)).thenReturn(Optional.of(sessionEntity));

        boolean result = this.validateToken.isValid(token, userDetails);

        assertThat(result).isTrue();
        verify(this.getUsernameFromToken, times(1)).get(token);
        verify(this.repository, times(1)).findByToken(token);
        verify(this.repository, times(0)).save(sessionEntity);
    }
}
