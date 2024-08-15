package com.br.walletwise.application.mocks;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.enums.CategoryEnum;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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

    public static User userFactory(UUID userId) {
        return new User(
                userId,
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
        return new Session(UUID.randomUUID(), session.getUserId(), session.getToken(), session.getCreationDate(), true);
    }

    public static FixedExpense fixedExpenseFactory() {
        return new FixedExpense(
                faker.lorem().word(),
                20,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant())
        );
    }

    public static FixedExpenseModel fixedExpenseModelFactory(User user) {
        String ownerFullName = user.getFirstname() + " " + user.getLastname();
        return new FixedExpenseModel(
                ownerFullName,
                MocksFactory.faker.lorem().paragraph(),
                15,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant())
        );
    }
}