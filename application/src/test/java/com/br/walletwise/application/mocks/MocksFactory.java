package com.br.walletwise.application.mocks;

import com.br.walletwise.core.domain.entities.User;
import com.github.javafaker.Faker;

public class MocksFactory {
    public  static Faker faker = new Faker();

    public static User userWithNoIdFactory(){
        String strongPassword = "Password!1234H";
        return new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);
    }

}
