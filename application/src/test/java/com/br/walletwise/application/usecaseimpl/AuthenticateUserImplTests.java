package com.br.walletwise.application.usecaseimpl;

import com.br.walletwise.application.gateway.AuthenticateUserGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.AuthenticateUserImpl;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.exception.UnauthorizedException;
import com.br.walletwise.usecase.AuthenticateUser;
import com.br.walletwise.usecase.FindByEmail;
import com.br.walletwise.usecase.FindByUsername;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

class AuthenticateUserImplTests {
    private AuthenticateUser authenticateUser;

    private FindByUsername findByUsername;
    private FindByEmail findByEmail;
    private AuthenticateUserGateway authenticateUserGateway;

    @BeforeEach
    void setUp() {
        this.findByUsername =  mock(FindByUsername.class);
        this.findByEmail =  mock(FindByEmail.class);
        this.authenticateUserGateway = mock(AuthenticateUserGateway.class);
        this.authenticateUser = new AuthenticateUserImpl(findByUsername,findByEmail, authenticateUserGateway);
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_username","any_username@test.com"})
    @DisplayName("Should throw UnauthorizedException if user does not exist by username or email")
    void shouldThrowUnauthorizedExceptionIfUserDoesNotExistByUsernameOrEmail(String usernameOrEmail) {
        String password = "any_password";

        when(this.findByEmail.find(usernameOrEmail)).thenReturn(Optional.empty());
        when(this.findByUsername.find(usernameOrEmail)).thenReturn(Optional.empty());

        Throwable exception = catchThrowable(() -> this.authenticateUser.authenticate(usernameOrEmail, password));

        assertThat(exception).isInstanceOf(UnauthorizedException.class);
        assertThat(exception.getMessage()).isEqualTo("Bad credentials.");
        if(usernameOrEmail.contains("@"))
            verify(this.findByEmail, times(1)).find(usernameOrEmail);
        else
            verify(this.findByUsername, times(1)).find(usernameOrEmail);
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_username","any_username@test.com"})
    @DisplayName("Should throw UnauthorizedException if password is wrong")
    void shouldThrowUnauthorizedExceptionIfPasswordIsWrong(String usernameOrEmail) {
        String password = "any_password";
        User user = MocksFactory.userFactory();

        when(this.findByEmail.find(usernameOrEmail)).thenReturn(Optional.of(user));
        when(this.findByUsername.find(usernameOrEmail)).thenReturn(Optional.of(user));
        when(this.authenticateUserGateway.authenticate(usernameOrEmail, password)).thenReturn(null);

        Throwable exception = catchThrowable(() -> this.authenticateUser.authenticate(usernameOrEmail, password));

        assertThat(exception).isInstanceOf(UnauthorizedException.class);
        assertThat(exception.getMessage()).isEqualTo("Bad credentials.");
        if(usernameOrEmail.contains("@"))
            verify(this.findByEmail, times(1)).find(usernameOrEmail);
        else
            verify(this.findByUsername, times(1)).find(usernameOrEmail);
    }
}