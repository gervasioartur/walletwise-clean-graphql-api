package com.br.walletwise.infra.mocks;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.entrypoint.dto.CreateUserRequest;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.UUID;

public class MocksFactory {
    public static Faker faker = new Faker();

    public static User userWithNoIdFactory() {
        return new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                "Password!1234H",
                true);
    }

    public static User userFactory() {
        return new User(
                UUID.randomUUID(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                "Password!1234H",
                true);
    }

    public static User userFactory(UserJpaEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.isActive()
        );
    }

    public static User userFactory(CreateUserRequest request) {
        return new User(
                request.firstname(),
                request.lastname(),
                request.username(),
                request.email(),
                request.password()
        );
    }

    public static UserJpaEntity userJpaEntityFactory() {
        return new UserJpaEntity(
                UUID.randomUUID(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                "Password!1234H",
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public static UserJpaEntity userJpaEntityFactory(User user){
        return UserJpaEntity
                .builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.isActive())
                .build();
    }

    public static UserJpaEntity userJpaEntityFactory(UserJpaEntity user){
        return UserJpaEntity
                .builder()
                .id(UUID.randomUUID())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public  static CreateUserRequest createUserRequestFactory(){
        return new CreateUserRequest(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                "Password!1234H");
    }
}