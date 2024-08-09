package com.br.walletwise.application.gateway;

import com.br.walletwise.core.domain.entity.User;

public interface CreateUserGateway {
    User create(User user);
}
