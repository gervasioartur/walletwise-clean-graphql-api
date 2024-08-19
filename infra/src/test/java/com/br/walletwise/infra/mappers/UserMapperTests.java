package com.br.walletwise.infra.mappers;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.entrypoint.dto.CreateUserInput;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
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
    @DisplayName("Should return user on map from creatUserRequest")
    void shouldReturnUserOnMapFromCreatUserRequest() {
        CreateUserInput request = MocksFactory.createUserInputFactory();

        User user = this.mapper.map(request);

        assertThat(user).isNotNull();
        assertThat(user.getFirstname()).isEqualTo(request.firstname());
        assertThat(user.getLastname()).isEqualTo(request.lastname());
        assertThat(user.getUsername()).isEqualTo(request.username());
        assertThat(user.getEmail()).isEqualTo(request.email());
        assertThat(user.getPassword()).isEqualTo(request.password());
    }

    @Test
    @DisplayName("Should return UserJpaEntity on map from user")
    void shouldReturnUserJpaEntityOnMapFromUser() {
        User user = MocksFactory.userFactory();

        UserJpaEntity userJpaEntity = this.mapper.map(user);

        assertThat(userJpaEntity.getId()).isEqualTo(user.getId());
        assertThat(userJpaEntity.getFirstname()).isEqualTo(user.getFirstname());
        assertThat(userJpaEntity.getLastname()).isEqualTo(user.getLastname());
        assertThat(userJpaEntity.getUsername()).isEqualTo(user.getUsername());
        assertThat(userJpaEntity.getEmail()).isEqualTo(user.getEmail());
        assertThat(userJpaEntity.getPassword()).isEqualTo(user.getPassword());
        assertThat(userJpaEntity.isActive()).isEqualTo(user.isActive());
    }

    @Test
    @DisplayName("Should return user on map from UserJpaEntity")
    void shouldReturnUserOnMapFromUserJpaEntity() {
        UserJpaEntity userJpaEntity = MocksFactory.userJpaEntityFactory();

        User user = this.mapper.map(userJpaEntity);

        assertThat(userJpaEntity.getId()).isEqualTo(user.getId());
        assertThat(userJpaEntity.getFirstname()).isEqualTo(user.getFirstname());
        assertThat(userJpaEntity.getLastname()).isEqualTo(user.getLastname());
        assertThat(userJpaEntity.getUsername()).isEqualTo(user.getUsername());
        assertThat(userJpaEntity.getEmail()).isEqualTo(user.getEmail());
        assertThat(userJpaEntity.getPassword()).isEqualTo(user.getPassword());
        assertThat(userJpaEntity.isActive()).isEqualTo(user.isActive());
    }
}
