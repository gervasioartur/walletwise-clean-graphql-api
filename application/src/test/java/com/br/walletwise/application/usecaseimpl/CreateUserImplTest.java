package com.br.walletwise.application.usecaseimpl;

import com.br.walletwise.application.gateway.CreateUserGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.CreateUserImpl;
import com.br.walletwise.core.domain.entities.User;
import com.br.walletwise.core.exception.BusinessException;
import com.br.walletwise.usecase.CreateUser;
import com.br.walletwise.usecase.EncodePassword;
import com.br.walletwise.usecase.FindByEmail;
import com.br.walletwise.usecase.FindByUsername;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static  org.assertj.core.api.Assertions.*;

class CreateUserImplTest {
    private CreateUser createUser;

    private FindByUsername findByUsername;
    private FindByEmail findByEmail;
    private EncodePassword encodePassword;
    private CreateUserGateway createUserGateway;

    @BeforeEach
    void setUp() {
        this.findByUsername =  mock(FindByUsername.class);
        this.findByEmail =  mock(FindByEmail.class);
        this.encodePassword =  mock(EncodePassword.class);
        this.createUserGateway =  mock(CreateUserGateway.class);

        this.createUser = new CreateUserImpl(findByUsername, findByEmail, encodePassword, createUserGateway);
    }


    @Test
    @DisplayName("Should throw BusinessException if username already exists")
    void shouldThrowBusinessExceptionIfUserNameAlreadyExists() {
        User user = MocksFactory.userWithNoIdFactory();

        when(this.findByUsername.find(user.getUsername())).thenReturn(Optional.of(user));

        Throwable exception = catchThrowable(() -> this.createUser.create(user));

        assertThat(exception).isInstanceOf(BusinessException.class);
        assertThat(exception.getMessage()).isEqualTo("Username already exists.");
        verify(this.findByUsername, times(1)).find(user.getUsername());
    }

}
