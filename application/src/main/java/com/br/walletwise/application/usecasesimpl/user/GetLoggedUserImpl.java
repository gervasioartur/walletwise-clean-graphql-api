package com.br.walletwise.application.usecasesimpl.user;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.user.GetLoggedUser;

public class GetLoggedUserImpl implements GetLoggedUser {
    private final GetLoggedUser getLoggedUser;

    public GetLoggedUserImpl(GetLoggedUser getLoggedUser) {
        this.getLoggedUser = getLoggedUser;
    }

    @Override
    public User get() {
        return this.getLoggedUser.get();
    }
}