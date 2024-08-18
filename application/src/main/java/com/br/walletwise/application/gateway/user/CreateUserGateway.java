package com.br.walletwise.application.gateway.user;

import com.br.walletwise.core.domain.entity.User;

public interface CreateUserGateway {
    User create(User user);
}
