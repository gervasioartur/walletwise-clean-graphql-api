package com.br.walletwise.usecase;

import com.br.walletwise.core.domain.entities.User;

import java.util.Optional;

public interface FindByUsername {
    Optional<User> find(String username);
}
