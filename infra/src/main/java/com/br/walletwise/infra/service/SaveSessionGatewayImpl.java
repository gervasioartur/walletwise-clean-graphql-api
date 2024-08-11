package com.br.walletwise.infra.service;

import com.br.walletwise.application.gateway.SaveSessionGateway;
import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.infra.mappers.SessionMapper;
import com.br.walletwise.infra.persistence.entity.SessionJpaEntity;
import com.br.walletwise.infra.persistence.repository.SessionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveSessionGatewayImpl implements SaveSessionGateway {
   private final SessionJpaRepository sessionJpaRepository;
   private final SessionMapper mapper;

    @Override
    public Session save(Session session) {
        SessionJpaEntity entity =  this.mapper.map(session);
        return this.mapper.map(this.sessionJpaRepository.save(entity));
    }
}
