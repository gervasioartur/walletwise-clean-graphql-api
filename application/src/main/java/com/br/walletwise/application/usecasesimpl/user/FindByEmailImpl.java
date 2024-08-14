package com.br.walletwise.application.usecasesimpl.user;

import com.br.walletwise.application.gateway.FindByEmailGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.user.FindByEmail;

import java.util.Optional;

public class FindByEmailImpl implements FindByEmail {
    private final FindByEmailGateway findByEmailGateway;

    public FindByEmailImpl(FindByEmailGateway findByEmailGateway) {
        this.findByEmailGateway = findByEmailGateway;
    }

    @Override
    public Optional<User> find(String email) {
        return this.findByEmailGateway.find(email);
    }
}
