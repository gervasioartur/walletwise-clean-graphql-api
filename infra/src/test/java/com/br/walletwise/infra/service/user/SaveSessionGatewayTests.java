package com.br.walletwise.infra.service.user;

import com.br.walletwise.application.gateway.user.SaveSessionGateway;
import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.infra.mapper.SessionMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.SessionJpaEntity;
import com.br.walletwise.infra.persistence.repository.SessionJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class SaveSessionGatewayTests {
    @Autowired
    private SaveSessionGateway saveSessionGateway;
    @MockBean
    private SessionMapper mapper;
    @MockBean
    private SessionJpaRepository sessionJpaRepository;

    @Test
    @DisplayName("Should save session")
    public void shouldSaveSession() {
        Session session = MocksFactory.sessionFactoryWithNoId();
        SessionJpaEntity sessionJpaEntity = MocksFactory.sessionJpaEntityFactory(session);

        SessionJpaEntity savedSessionEntity = MocksFactory.sessionJpaEntityFactory(sessionJpaEntity);
        Session savedSession = MocksFactory.sessionFactory(savedSessionEntity);

        when(this.mapper.map(session)).thenReturn(sessionJpaEntity);
        when(this.sessionJpaRepository.save(sessionJpaEntity)).thenReturn(savedSessionEntity);
        when(this.mapper.map(savedSessionEntity)).thenReturn(savedSession);

        Session result = saveSessionGateway.save(session);

        assertThat(result.getId()).isEqualTo(savedSessionEntity.getId());
        assertThat(result.getToken()).isEqualTo(savedSessionEntity.getToken());
        assertThat(result.getUserId()).isEqualTo(savedSessionEntity.getUser().getId());
        assertThat(result.getCreationDate()).isEqualTo(savedSessionEntity.getCreationDate());
    }
}
