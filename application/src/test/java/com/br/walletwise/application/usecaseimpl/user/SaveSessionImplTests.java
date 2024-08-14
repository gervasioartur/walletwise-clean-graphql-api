package com.br.walletwise.application.usecaseimpl.user;

import com.br.walletwise.application.gateway.SaveSessionGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.user.SaveSessionImpl;
import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.usecase.user.SaveSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SaveSessionImplTests {
    @Test
    @DisplayName("Should save session")
    public void testSaveSession() {
        SaveSessionGateway sessionGateway = mock(SaveSessionGateway.class);

        Session session = MocksFactory.sessionWithNoIdFactory();
        Session savedSession = MocksFactory.sessionFactory(session);

        when(sessionGateway.save(session)).thenReturn(savedSession);

        SaveSession saveSession = new SaveSessionImpl(sessionGateway);
        Session result =  saveSession.save(session);

        assertThat(result.getId()).isEqualTo(savedSession.getId());
        assertThat(result.getUserId()).isEqualTo(savedSession.getUserId());
        assertThat(result.getToken()).isEqualTo(savedSession.getToken());
        assertThat(result.getCreationDate()).isEqualTo(savedSession.getCreationDate());
    }
}
