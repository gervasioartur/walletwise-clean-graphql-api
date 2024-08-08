package com.br.walletwise.core.entities;

import com.br.walletwise.core.domain.entities.User;
import com.br.walletwise.core.exception.DomainException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class UserTests {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw BusinessException when trying to build user with firstname empty or null")
    void shouldThrowBusinessExceptionWhenTryingToBuildUserWithFirstNameEmptyOrNull(String firstname) {
        Throwable exception = Assertions.catchThrowable( () -> new User(
                null,
                "",
                "any_last_name",
                "any_username",
                "any_email",
                "any_password",
                true));

        Assertions.assertThat(exception).isInstanceOf(DomainException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("Firstname is required.");
    }
}