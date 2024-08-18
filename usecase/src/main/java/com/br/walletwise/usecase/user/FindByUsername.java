package com.br.walletwise.usecase.user;

import com.br.walletwise.core.domain.entity.User;

import java.util.Optional;

public interface FindByUsername {
    Optional<User> find(String username);
}
