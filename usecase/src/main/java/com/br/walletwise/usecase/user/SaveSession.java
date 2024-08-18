package com.br.walletwise.usecase.user;

import com.br.walletwise.core.domain.entity.Session;

public interface SaveSession {
    Session save(Session session);
}