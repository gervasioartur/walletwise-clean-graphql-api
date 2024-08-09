package com.br.walletwise.infra.mappers;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.api.dto.CreateUserRequest;
import com.br.walletwise.infra.mocks.MocksFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserMapperTests {
    @Autowired
    private UserMapper mapper;

    @Test
    @DisplayName("Should return user on map from creat user request ")
    void shouldReturnUserOnMapFromCreatUserRequest() {
        CreateUserRequest request = MocksFactory.createUserRequestFactory();

        User user = this.mapper.map(request);

        assertThat(user).isNotNull();
        assertThat(user.getFirstname()).isEqualTo(request.firstname());
        assertThat(user.getLastname()).isEqualTo(request.lastname());
        assertThat(user.getUsername()).isEqualTo(request.username());
        assertThat(user.getEmail()).isEqualTo(request.email());
        assertThat(user.getPassword()).isEqualTo(request.password());
    }
}
