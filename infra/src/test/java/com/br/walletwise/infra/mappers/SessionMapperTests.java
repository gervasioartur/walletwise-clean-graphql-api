package com.br.walletwise.infra.mappers;

import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.SessionJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SessionMapperTests {
    @Autowired
    private SessionMapper mapper;

    @Test
    @DisplayName("Should return SessionJpaEntity")
    public void shouldReturnSessionJpaEntity() {
        Session session = MocksFactory.sessionFactoryId();
        SessionJpaEntity entity = this.mapper.map(session);

        assertThat(entity.getId()).isEqualTo(session.getId());
        assertThat(entity.getUser().getId()).isEqualTo(session.getUserId());
        assertThat(entity.getToken()).isEqualTo(session.getToken());
        assertThat(entity.getCreationDate()).isEqualTo(session.getCreationDate());
    }

    @Test
    @DisplayName("Should return Session entity")
    public void shouldReturnSessionEntity() {
        SessionJpaEntity entity = MocksFactory.sessionJpaEntityFactory();
        Session session = this.mapper.map(entity);

        assertThat(entity.getId()).isEqualTo(session.getId());
        assertThat(entity.getUser().getId()).isEqualTo(session.getUserId());
        assertThat(entity.getToken()).isEqualTo(session.getToken());
        assertThat(entity.getCreationDate()).isEqualTo(session.getCreationDate());
    }
}
