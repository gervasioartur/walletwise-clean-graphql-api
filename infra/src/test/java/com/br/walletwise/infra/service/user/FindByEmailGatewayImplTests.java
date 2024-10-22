package com.br.walletwise.infra.service.user;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.mapper.UserMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import com.br.walletwise.infra.persistence.repository.UserJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class FindByEmailGatewayImplTests {
    @Autowired
    FindByEmailGatewayImpl findByEmailJpaGateway;

    @MockBean
    private UserJpaRepository userJpaRepository;
    @MockBean
    private UserMapper mapper;

    @Test
    @DisplayName("Should return optional of user")
    void shouldReturnOptionalOfUser() {
        User user = MocksFactory.userFactory();

        UserJpaEntity entity = MocksFactory.userJpaEntityFactory(user);

        when(this.userJpaRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(entity));
        when(this.mapper.map(entity)).thenReturn(user);

        Optional<User> result = this.findByEmailJpaGateway.find(user.getEmail());

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(user.getId());
        assertThat(result.get().getEmail()).isEqualTo(user.getEmail());
        verify(this.userJpaRepository, times(1)).findByEmail(user.getEmail());
        verify(this.mapper, times(1)).map(entity);
    }
}
