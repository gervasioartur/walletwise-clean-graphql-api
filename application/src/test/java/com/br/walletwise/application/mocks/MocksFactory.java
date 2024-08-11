package com.br.walletwise.application.mocks;

import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.core.domain.entity.User;
import com.github.javafaker.Faker;

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

    public static Session sessionWithNoIdFactory() {
        return new Session(UUID.randomUUID(), UUID.randomUUID().toString());
    }

    public static Session sessionFactory(Session session) {
        return new Session(UUID.randomUUID(), session.getUserId(),session.getToken(),session.getCreationDate(),true);
    }
}
