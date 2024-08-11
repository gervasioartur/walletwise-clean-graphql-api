package com.br.walletwise.application.gateway;

import com.br.walletwise.core.domain.entity.Session;

public interface SaveSessionGateway {
    Session save(Session session);
}