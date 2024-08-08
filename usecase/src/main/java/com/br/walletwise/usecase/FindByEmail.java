package com.br.walletwise.usecase;

import com.br.walletwise.core.domain.entities.User;

import java.util.Optional;

public interface FindByEmail {
    Optional<User> find(String email);
}
