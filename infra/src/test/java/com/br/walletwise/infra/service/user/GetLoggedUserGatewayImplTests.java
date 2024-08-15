package com.br.walletwise.infra.service.user;

import com.br.walletwise.application.gateway.user.GetLoggedUserGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.infra.mappers.UserMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import com.br.walletwise.infra.persistence.repository.UserJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetLoggedUserGatewayImplTests {
    @Autowired
    private GetLoggedUserGateway getLoggedUserGateway;
    @MockBean
    private UserJpaRepository repository;
    @MockBean
    private UserMapper mapper;

    @Test
    @DisplayName("Should throw NotFoundException if user does not exist")
    void shouldThrowNotFoundException() {
        String username = MocksFactory.faker.name().username();
        User user = MocksFactory.userFactory();

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "senha123");
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(repository.findByUsername(username)).thenReturn(Optional.empty());
        Throwable exception = catchThrowable(() -> this.getLoggedUserGateway.get());

        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo("Username not found.");
        verify(repository, times(1)).findByUsername(username);
    }

    @Test
    @DisplayName("Should return logged user")
    void shouldReturnLoggedUser() {
        String username = MocksFactory.faker.name().username();
        User user = MocksFactory.userFactory();
        UserJpaEntity userJpaEntity = MocksFactory.userJpaEntityFactory(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "senha123");
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(repository.findByUsername(username)).thenReturn(Optional.of(userJpaEntity));
        when(this.mapper.map(userJpaEntity)).thenReturn(user);

        this.getLoggedUserGateway.get();
        verify(repository, times(1)).findByUsername(username);
    }
}