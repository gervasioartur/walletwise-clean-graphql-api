package com.br.walletwise.application.usecasesimpl.user;

import com.br.walletwise.application.gateway.user.SaveSessionGateway;
import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.usecase.user.SaveSession;

public class SaveSessionImpl implements SaveSession {
   private final SaveSessionGateway sessionGateway;

    public SaveSessionImpl(SaveSessionGateway sessionGateway) {
        this.sessionGateway = sessionGateway;
    }

    @Override
    public Session save(Session session) {
        session.setActive(true);
        return this.sessionGateway.save(session);
    }
}
