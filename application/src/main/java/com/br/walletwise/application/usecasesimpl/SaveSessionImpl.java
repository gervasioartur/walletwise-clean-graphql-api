package com.br.walletwise.application.usecasesimpl;

import com.br.walletwise.application.gateway.SaveSessionGateway;
import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.usecase.SaveSession;

public class SaveSessionImpl implements SaveSession {
   private final SaveSessionGateway sessionGateway;

    public SaveSessionImpl(SaveSessionGateway sessionGateway) {
        this.sessionGateway = sessionGateway;
    }

    @Override
    public Session save(Session session) {
        return this.sessionGateway.save(session);
    }
}
