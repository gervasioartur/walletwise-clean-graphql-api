package com.br.walletwise.infra.mappers;

import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.infra.persistence.entity.SessionJpaEntity;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {
    public SessionJpaEntity map(Session session){
        return  SessionJpaEntity
                .builder()
                .id(session.getId())
                .user(UserJpaEntity.builder().id(session.getUserId()).build())
                .creationDate(session.getCreationDate())
                .build();
    }

    public Session map(SessionJpaEntity entity){
        return new Session(entity.getId(), entity.getUser().getId(),entity.getToken(), entity.getCreationDate());
    }
}
