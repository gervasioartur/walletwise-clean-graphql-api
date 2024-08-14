package com.br.walletwise.application.usecaseimpl;

import com.br.walletwise.application.gateway.FindByUsernameGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.user.FindByUsernameImpl;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.user.FindByUsername;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByUsernameImplTests {

    @Test
    @DisplayName("Should return option of user")
    void shouldReturnOptionOfUser() {
        User user = MocksFactory.userWithNoIdFactory();

        FindByUsernameGateway findByUsernameGateway = mock(FindByUsernameGateway.class);
        FindByUsername findByUsername = new FindByUsernameImpl(findByUsernameGateway);

        when(findByUsernameGateway.find(user.getUsername())).thenReturn(Optional.of(user));

        Optional<User> userResult = findByUsername.find(user.getUsername());

        assertThat(userResult).isPresent();
        assertThat(userResult.get().getId()).isEqualTo(user.getId());
        assertThat(userResult.get().getUsername()).isEqualTo(user.getUsername());
    }
}
