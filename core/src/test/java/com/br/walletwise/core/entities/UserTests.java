package com.br.walletwise.core.entities;

import com.br.walletwise.core.domain.entities.User;
import com.br.walletwise.core.exception.DomainException;
import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class UserTests {
    private final Faker faker = new Faker();

    String strongPassword = "Password!1234H";

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with firstname empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWithFirstNameEmptyOrNull(String firstname) {
        Throwable exception = Assertions.catchThrowable( () -> new User(
                null,
                firstname,
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Firstname is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to set firstname with empty or null value")
    void shouldThrowDomainExceptionWhenTryingToSetFirstNameWithEmptyOrNullValue(String firstname) {
        User user = new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);

        Throwable exception = Assertions.catchThrowable(() -> user.setFirstname(firstname) );

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Firstname is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with lastname empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWitLastNameEmptyOrNull(String lastname) {
        Throwable exception = catchThrowable( () -> new User(
                null,
                faker.name().firstName(),
                lastname,
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Lastname is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to set lastname with empty or null value")
    void shouldThrowDomainExceptionWhenTryingToSetLastnameWithEmptyOrNullValue(String lastname) {
        User user = new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);

        Throwable exception = catchThrowable(() -> user.setLastname(lastname) );

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Lastname is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with username empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWitUsernameEmptyOrNull(String username) {
        Throwable exception = catchThrowable( () -> new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                username,
                faker.internet().emailAddress(),
                strongPassword,
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Username is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to set Username with empty or null value")
    void shouldThrowDomainExceptionWhenTryingToSetUsernameWithEmptyOrNullValue(String Username) {
        User user = new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);

        Throwable exception = catchThrowable(() -> user.setUsername(Username) );

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Username is required.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"@any_username","any@username", "any_username@"})
    @DisplayName("Should throw DomainException when trying to build user with invalid Username")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWithInvalidUsername(String username) {

        Throwable exception = catchThrowable(() ->  new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                username,
                faker.internet().emailAddress(),
                strongPassword,
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Username is invalid.");
    }


    @ParameterizedTest
    @ValueSource(strings = {"@any_username","any@username", "any_username@"})
    @DisplayName("Should throw DomainException when trying to update user with invalid Username")
    void shouldThrowDomainExceptionWhenTryingToUpdateUserWithInvalidUsername(String username) {
        User user = new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);

        Throwable exception = catchThrowable(() ->  user.setUsername(username) );

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Username is invalid.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with email empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWitEmailEmptyOrNull(String email) {
        Throwable exception = catchThrowable( () -> new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                email,
                strongPassword,
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("E-mail is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to update user with email empty or null")
    void shouldThrowDomainExceptionWhenTryingToUpdateUserWitEmailEmptyOrNull(String email) {
        User user = new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);

        Throwable exception = catchThrowable( () -> user.setEmail(email) );

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("E-mail is required.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_email", "@email.com", "any_@emal"})
    @DisplayName("Should throw DomainException when trying to build user with invalid email")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWitInvalidEmail(String email) {
        Throwable exception = catchThrowable( () -> new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                email,
                strongPassword,
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("E-mail is invalid.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_email", "@email.com", "any_@emal"})
    @DisplayName("Should throw DomainException when trying to update user with invalid email")
    void shouldThrowDomainExceptionWhenTryingToUpdateUserWitInvalidEmail(String email) {
        User user = new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);

        Throwable exception = catchThrowable( () -> user.setEmail(email));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("E-mail is invalid.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with password empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWithPasswordEmptyOrNull(String password) {

        Throwable exception = catchThrowable( () -> new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                password,
                true) );

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Password is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to update user with password empty or null")
    void shouldThrowDomainExceptionWhenTryingToUpdateUserWithPasswordEmptyOrNull(String password) {
        User user = new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);

        Throwable exception = catchThrowable( () ->  user.setPassword(password));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Password is required.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "any_password", "123password", "123_password"})
    @DisplayName("Should throw DomainException when trying to build user with weak password")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWithWeakPassword(String password) {

        Throwable exception = catchThrowable( () -> new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                password,
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Password too weak! Must contain at " +
                "least 8 characters,one uppercase letter, a special character and a number.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "any_password", "123password", "123_password"})
    @DisplayName("Should throw DomainException when trying to update user with weak password")
    void shouldThrowDomainExceptionWhenTryingToUpdateUserWithWeakPassword(String password) {
        User user =  new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);
        Throwable exception = catchThrowable( () -> user.setPassword(password));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Password too weak! Must contain at " +
                "least 8 characters,one uppercase letter, a special character and a number.");
    }

    @Test
    @DisplayName("Should return correct values on build success")
    void shouldThrowDomainExceptionWhenTryingToUpdateUserWithWeakPassword() {
        UUID id = UUID.randomUUID();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = strongPassword;
        boolean isActive = true;

        User user =  new User(id, firstName, lastName, username, email, password, isActive);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getFirstname()).isEqualTo(firstName);
        assertThat(user.getLastname()).isEqualTo(lastName);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.isActive()).isEqualTo(isActive);
    }
}