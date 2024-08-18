package com.br.walletwise.application.gateway.user;

import com.br.walletwise.core.domain.entity.User;

import java.util.Optional;

public interface FindByEmailGateway {
    Optional<User> find(String email);
}
