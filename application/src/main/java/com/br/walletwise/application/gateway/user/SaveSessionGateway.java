package com.br.walletwise.application.gateway.user;

import com.br.walletwise.core.domain.entity.Session;

public interface SaveSessionGateway {
    Session save(Session session);
}