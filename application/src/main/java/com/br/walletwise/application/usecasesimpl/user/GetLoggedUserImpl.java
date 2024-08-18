package com.br.walletwise.application.usecasesimpl.user;

import com.br.walletwise.application.gateway.user.GetLoggedUserGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.user.GetLoggedUser;

public class GetLoggedUserImpl implements GetLoggedUser {
    private final GetLoggedUserGateway getLoggedUserGateway;

    public GetLoggedUserImpl(GetLoggedUserGateway getLoggedUserGateway) {
        this.getLoggedUserGateway = getLoggedUserGateway;
    }

    @Override
    public User get() {
        return this.getLoggedUserGateway.get();
    }
}