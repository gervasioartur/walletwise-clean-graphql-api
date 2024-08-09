package com.br.walletwise.application.usecaseimpl;

import com.br.walletwise.application.gateway.FindByEmailGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.FindByEmailImpl;
import com.br.walletwise.core.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByEmailImplTests {

    @Test
    @DisplayName("Should return option of user")
    void shouldReturnOptionOfUser() {
        User user = MocksFactory.userWithNoIdFactory();

        FindByEmailGateway findByEmailGateway = mock(FindByEmailGateway.class);
        FindByEmailImpl findByEmailImpl = new FindByEmailImpl(findByEmailGateway);

        when(findByEmailGateway.find(user.getEmail())).thenReturn(Optional.of(user));

        Optional<User> userResult = findByEmailImpl.find(user.getEmail());

       assertThat(userResult).isPresent();
       assertThat(userResult.get().getId()).isEqualTo(user.getId());
       assertThat(userResult.get().getEmail()).isEqualTo(user.getEmail());
    }

}
