package com.br.walletwise.infra.service;

import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import com.br.walletwise.infra.persistence.repository.UserJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoadUserByUsernameGatewayImplTests {
    @Autowired
    private LoadUserByUsernameGatewayImpl loadUserByUsernameGateway;
    @MockBean
    private UserJpaRepository userJpaRepository;

    @Test
    @DisplayName("Should throw UsernameNotFoundException if user does no exist on find by username")
    public void shouldThrowUsernameNotFoundExceptionIfUserDoesNotExistOnFindByUsername() {
        String username = "any_username";
        when(this.userJpaRepository.findByUsername(username)).thenReturn(Optional.empty());
        Throwable exception = catchThrowable(() -> this.loadUserByUsernameGateway.loadUserByUsername(username));
        assertThat(exception).isInstanceOf(UsernameNotFoundException.class);
    }

    @Test
    @DisplayName("Should return user on find by username")
    void shouldReturnUserOnFindByUsername() {
        String username = "any_username";
        UserJpaEntity userJpaEntity = MocksFactory.userJpaEntityFactory();

        when(this.userJpaRepository.findByUsername(username)).thenReturn(Optional.of(userJpaEntity));

        UserDetails result = this.loadUserByUsernameGateway.loadUserByUsername(username);

        assertThat(result.getUsername()).isEqualTo(userJpaEntity.getUsername());
        assertThat(result.getAuthorities()).isEqualTo(userJpaEntity.getAuthorities());
        assertThat(result.isAccountNonExpired()).isEqualTo(userJpaEntity.isAccountNonExpired());
        assertThat(result.isAccountNonLocked()).isEqualTo(userJpaEntity.isAccountNonLocked());
        assertThat(result.isCredentialsNonExpired()).isEqualTo(userJpaEntity.isCredentialsNonExpired());
        assertThat(result.isEnabled()).isEqualTo(userJpaEntity.isEnabled());
        verify(this.userJpaRepository).findByUsername(username);
    }
}