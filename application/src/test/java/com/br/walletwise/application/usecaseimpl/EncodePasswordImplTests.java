package com.br.walletwise.application.usecaseimpl;

import com.br.walletwise.application.gateway.EncodePasswordGateway;
import com.br.walletwise.application.usecasesimpl.user.EncodePasswordImpl;
import com.br.walletwise.usecase.user.EncodePassword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EncodePasswordImplTests {

    @Test
    @DisplayName("Should return option of encoded password")
    void shouldReturnOptionOfEncodedPassword() {
        String password = "password";
        String encodedPassword = "encodedPassword";

        EncodePasswordGateway encodePasswordGateway = mock(EncodePasswordGateway.class);
        EncodePassword encodePassword = new EncodePasswordImpl(encodePasswordGateway);

        when(encodePasswordGateway.encode(password)).thenReturn(encodedPassword);

        String result = encodePassword.encode(password);

        assertThat(result).isEqualTo(encodedPassword);
    }
}
