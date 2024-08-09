package com.br.walletwise.core.entity;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.exception.DomainException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class UserTests {
    private final Faker faker = new Faker();

    String strongPassword = "Password!1234H";

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with firstname empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWithFirstNameEmptyOrNull(String firstname) {
        Throwable exception = catchThrowable(() -> new User(
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

        Throwable exception = catchThrowable(() -> user.setFirstname(firstname));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Firstname is required.");
    }

    @Test
    @DisplayName("Should return null when trying to set firstname with valid input")
    void shouldReturnNullWhenTryingToSetFirstNameWithValidInput() {
        User user = new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);

        String updateField =  faker.name().firstName();

        user.setFirstname(updateField);

        assertThat(user.getFirstname()).isEqualTo(updateField);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with lastname empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWitLastNameEmptyOrNull(String lastname) {
        Throwable exception = catchThrowable(() -> new User(
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

    @Test
    @DisplayName("Should return null when trying to set Lastname with valid input")
    void shouldReturnNullWhenTryingToSetLastNameWithValidInput() {
        User user = new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);

        String updateField =  faker.name().lastName();

        user.setLastname(updateField);

        assertThat(user.getLastname()).isEqualTo(updateField);
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

        Throwable exception = catchThrowable(() -> user.setLastname(lastname));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Lastname is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with username empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWitUsernameEmptyOrNull(String username) {
        Throwable exception = catchThrowable(() -> new User(
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

    @Test
    @DisplayName("Should return null when trying to set username with valid input")
    void shouldReturnNullWhenTryingToSetUsernameWithValidInput() {
        User user = new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);

        String updateField =  faker.name().username();

        user.setUsername(updateField);

        assertThat(user.getUsername()).isEqualTo(updateField);
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

        Throwable exception = catchThrowable(() -> user.setUsername(Username));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Username is required.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"@any_username", "any@username", "any_username@"})
    @DisplayName("Should throw DomainException when trying to build user with invalid Username")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWithInvalidUsername(String username) {

        Throwable exception = catchThrowable(() -> new User(
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
    @ValueSource(strings = {"@any_username", "any@username", "any_username@"})
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

        Throwable exception = catchThrowable(() -> user.setUsername(username));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Username is invalid.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with email empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWitEmailEmptyOrNull(String email) {
        Throwable exception = catchThrowable(() -> new User(
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

        Throwable exception = catchThrowable(() -> user.setEmail(email));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("E-mail is required.");
    }

    @Test
    @DisplayName("Should return null when trying to set Email with valid input")
    void shouldReturnNullWhenTryingToSetEmailWithValidInput() {
        User user = new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                strongPassword,
                true);

        String updateField =  faker.internet().emailAddress();

        user.setEmail(updateField);

        assertThat(user.getEmail()).isEqualTo(updateField);
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_email", "@email.com", "any_@emal"})
    @DisplayName("Should throw DomainException when trying to build user with invalid email")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWitInvalidEmail(String email) {
        Throwable exception = catchThrowable(() -> new User(
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

        Throwable exception = catchThrowable(() -> user.setEmail(email));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("E-mail is invalid.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException when trying to build user with password empty or null")
    void shouldThrowDomainExceptionWhenTryingToBuildUserWithPasswordEmptyOrNull(String password) {

        Throwable exception = catchThrowable(() -> new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                password,
                true));

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

        Throwable exception = catchThrowable(() -> user.setPassword(password));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Password is required.");
    }

    @Test
    @DisplayName("Should return correct values on build success with id")
    void shouldReturnCorrectValuesOnBuildSuccessWithId() {
        UUID id = UUID.randomUUID();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = strongPassword;
        boolean isActive = true;

        User user = new User(id, firstName, lastName, username, email, password, isActive);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getFirstname()).isEqualTo(firstName);
        assertThat(user.getLastname()).isEqualTo(lastName);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.isActive()).isEqualTo(isActive);
    }

    @Test
    @DisplayName("Should return correct values on build success")
    void shouldReturnCorrectValuesOnBuildSuccessWithNoId() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = strongPassword;

        User user = new User(firstName, lastName, username, email, password);

        assertThat(user.getId()).isNull();
        assertThat(user.getFirstname()).isEqualTo(firstName);
        assertThat(user.getLastname()).isEqualTo(lastName);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.isActive()).isFalse();
    }

    @Test
    @DisplayName("Should throw Domain exception on build failure")
    void shouldThrowDomainExceptionOnBuildFailure() {
        String lastName = faker.name().lastName();
        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = strongPassword;

       Throwable exception = catchThrowable(() -> new User("", lastName, username, email, password));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Firstname is required.");
    }

    @Test
    @DisplayName("Should return correct values on update id and active success")
    void shouldReturnCorrectValuesOnUpdateIdAndActiveSuccess() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = strongPassword;
        UUID id = UUID.randomUUID();
        boolean active =  true;

        User user = new User(firstName, lastName, username, email, password);
        user.setId(id);
        user.setActive(active);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getFirstname()).isEqualTo(firstName);
        assertThat(user.getLastname()).isEqualTo(lastName);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.isActive()).isTrue();
    }
}