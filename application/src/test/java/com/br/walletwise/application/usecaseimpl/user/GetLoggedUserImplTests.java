package com.br.walletwise.application.usecaseimpl.user;

import com.br.walletwise.application.gateway.user.GetLoggedUserGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.user.GetLoggedUserImpl;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GetLoggedUserImplTests {
    private GetLoggedUser getLoggedUser;
    private GetLoggedUserGateway getLoggedUserGateway;

    @BeforeEach
    public void setUp() {
        this.getLoggedUserGateway = mock(GetLoggedUserGateway.class);
        this.getLoggedUser = new GetLoggedUserImpl(getLoggedUserGateway);
    }

    @Test
    @DisplayName("Should return logged user")
    public void shouldReturnLoggedUser() {
        User user = MocksFactory.userFactory();
        when(this.getLoggedUserGateway.get()).thenReturn(user);
        User result = this.getLoggedUser.get();
        assertThat(result.getId()).isEqualTo(user.getId());
        verify(this.getLoggedUserGateway, timeout(1)).get();
    }
}
