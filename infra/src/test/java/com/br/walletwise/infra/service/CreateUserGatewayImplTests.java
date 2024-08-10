package com.br.walletwise.infra.service;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import com.br.walletwise.infra.persistence.repository.IUserJpaRepository;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.mappers.UserMapper;
import com.br.walletwise.infra.service.CreateUserGatewayImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class CreateUserGatewayImplTests {
    @Autowired
    private CreateUserGatewayImpl createUserJpaGateway;
    @MockBean
    private IUserJpaRepository userJpaRepository;
    @MockBean
    private UserMapper mapper;

    @Test
    @DisplayName("Should save user")
    void shouldSaveUser() {
        User user = MocksFactory.userWithNoIdFactory();
        UserJpaEntity userJpaEntity = MocksFactory.userJpaEntityFactory(user);

        UserJpaEntity savedUserJpaEntity = MocksFactory.userJpaEntityFactory(userJpaEntity);
        User savedUser = MocksFactory.userFactory(savedUserJpaEntity);

        when(this.mapper.map(user)).thenReturn(userJpaEntity);
        when(this.userJpaRepository.save(userJpaEntity)).thenReturn(savedUserJpaEntity);
        when(this.mapper.map(savedUserJpaEntity)).thenReturn(savedUser);

        User result = this.createUserJpaGateway.create(user);

        assertThat(result.getId()).isEqualTo(savedUser.getId());
        verify(this.mapper,times(1)).map(user);
        verify(this.userJpaRepository,times(1)).save(userJpaEntity);
        verify(this.mapper,times(1)).map(savedUserJpaEntity);
    }
}
