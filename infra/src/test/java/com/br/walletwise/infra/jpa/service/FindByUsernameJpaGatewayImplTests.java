package com.br.walletwise.infra.jpa.service;

import com.br.walletwise.application.gateway.FindByUsernameGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.jpa.entity.UserJpaEntity;
import com.br.walletwise.infra.jpa.repository.IUserJpaRepository;
import com.br.walletwise.infra.mappers.UserMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FindByUsernameJpaGatewayImplTests {
    @Autowired
    FindByUsernameGateway findByUsernameGateway;

    @MockBean
    private IUserJpaRepository userJpaRepository;
    @MockBean
    private UserMapper mapper;

    @Test
    @DisplayName("Should return optional of user")
    void shouldReturnOptionalOfUser() {
        User user = MocksFactory.userFactory();

        UserJpaEntity entity = MocksFactory.userJpaEntityFactory(user);

        when(this.userJpaRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(entity));
        when(this.mapper.map(entity)).thenReturn(user);

        Optional<User> result = this.findByUsernameGateway.find(user.getUsername());

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(user.getId());
        assertThat(result.get().getEmail()).isEqualTo(user.getEmail());
        verify(this.userJpaRepository, times(1)).findByUsername(user.getUsername());
        verify(this.mapper, times(1)).map(entity);
    }
}
