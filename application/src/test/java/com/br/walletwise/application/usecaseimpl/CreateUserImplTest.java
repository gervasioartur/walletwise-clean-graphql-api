package com.br.walletwise.application.usecaseimpl;

import com.br.walletwise.application.gateway.CreateUserGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.CreateUserImpl;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.exception.ConflictException;
import com.br.walletwise.usecase.user.CreateUser;
import com.br.walletwise.usecase.user.EncodePassword;
import com.br.walletwise.usecase.user.FindByEmail;
import com.br.walletwise.usecase.user.FindByUsername;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

class CreateUserImplTest {
    private CreateUser createUser;

    private FindByUsername findByUsername;
    private FindByEmail findByEmail;
    private EncodePassword encodePassword;
    private CreateUserGateway createUserGateway;

    @BeforeEach
    void setUp() {
        this.findByUsername = mock(FindByUsername.class);
        this.findByEmail = mock(FindByEmail.class);
        this.encodePassword = mock(EncodePassword.class);
        this.createUserGateway = mock(CreateUserGateway.class);

        this.createUser = new CreateUserImpl(findByUsername, findByEmail, encodePassword, createUserGateway);
    }


    @Test
    @DisplayName("Should throw BusinessException if username already exists")
    void shouldThrowBusinessExceptionIfUserNameAlreadyExists() {
        User user = MocksFactory.userWithNoIdFactory();

        when(this.findByUsername.find(user.getUsername())).thenReturn(Optional.of(user));

        Throwable exception = catchThrowable(() -> this.createUser.create(user));

        assertThat(exception).isInstanceOf(ConflictException.class);
        assertThat(exception.getMessage()).isEqualTo("Username already taken.");
        verify(this.findByUsername, times(1)).find(user.getUsername());
    }

    @Test
    @DisplayName("Should throw BusinessException if email is already in use")
    void shouldThrowBusinessExceptionIdEmailIsAlreadyInUse() {
        User user = MocksFactory.userWithNoIdFactory();

        when(this.findByUsername.find(user.getUsername())).thenReturn(Optional.empty());
        when(this.findByEmail.find(user.getEmail())).thenReturn(Optional.of(user));


        Throwable exception = catchThrowable(() -> this.createUser.create(user));

        assertThat(exception).isInstanceOf(ConflictException.class);
        assertThat(exception.getMessage()).isEqualTo("E-mail already in use.");
        verify(this.findByUsername, times(1)).find(user.getUsername());
        verify(this.findByEmail, times(1)).find(user.getEmail());
    }

    @Test
    @DisplayName("Should create user on success")
    void shouldCreateUserOnSuccess() {
        User user = MocksFactory.userWithNoIdFactory();
        String encodedPassword = UUID.randomUUID().toString();
        User userWithEncodedPassword = user;
        userWithEncodedPassword.setPassword(encodedPassword);

        when(this.findByUsername.find(user.getUsername())).thenReturn(Optional.empty());
        when(this.findByEmail.find(user.getEmail())).thenReturn(Optional.empty());
        when(this.encodePassword.encode(user.getPassword())).thenReturn(encodedPassword);

        this.createUser.create(user);

        verify(this.findByUsername, times(1)).find(user.getUsername());
        verify(this.findByEmail, times(1)).find(user.getEmail());
        verify(this.encodePassword, times(1)).encode(user.getPassword());
        verify(this.createUserGateway, times(1)).create(userWithEncodedPassword);
    }
}
