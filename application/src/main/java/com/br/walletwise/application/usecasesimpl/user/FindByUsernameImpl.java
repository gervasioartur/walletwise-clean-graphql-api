package com.br.walletwise.application.usecasesimpl.user;

import com.br.walletwise.application.gateway.FindByUsernameGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.user.FindByUsername;

import java.util.Optional;

public class FindByUsernameImpl implements FindByUsername {
    private final FindByUsernameGateway findByUsernameGateway;

    public FindByUsernameImpl(FindByUsernameGateway findByUsernameGateway) {
        this.findByUsernameGateway = findByUsernameGateway;
    }

    @Override
    public Optional<User> find(String username) {
        return findByUsernameGateway.find(username);
    }
}
