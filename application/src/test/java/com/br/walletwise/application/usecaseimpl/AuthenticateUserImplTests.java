package com.br.walletwise.application.usecaseimpl;

import com.br.walletwise.application.usecasesimpl.AuthenticateUserImpl;
import com.br.walletwise.core.exception.UnauthorizedException;
import com.br.walletwise.usecase.AuthenticateUser;
import com.br.walletwise.usecase.FindByEmail;
import com.br.walletwise.usecase.FindByUsername;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticateUserImplTests {
    private AuthenticateUser authenticateUser;

    private FindByUsername findByUsername;
    private FindByEmail findByEmail;

    @BeforeEach
    void setUp() {
        this.findByUsername =  mock(FindByUsername.class);
        this.findByEmail =  mock(FindByEmail.class);
        this.authenticateUser = new AuthenticateUserImpl(findByUsername,findByEmail);
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_username","any_username@test.com"})
    @DisplayName("Should throw UnauthorizedException if user does not exist by username or email")
    void shouldThrowUnauthorizedExceptionIfUserDoesNotExistByUsernameOrEmail(String usernameOrEmail) {
        String password = "password";

        if(usernameOrEmail.contains("@"))
            when(this.findByEmail.find(usernameOrEmail)).thenReturn(Optional.empty());
        else
            when(this.findByUsername.find(usernameOrEmail)).thenReturn(Optional.empty());

        Throwable exception = catchThrowable(() -> this.authenticateUser.authenticate(usernameOrEmail, password));

        assertThat(exception).isInstanceOf(UnauthorizedException.class);
        assertThat(exception.getMessage()).isEqualTo("Bad credentials.");
    }
}