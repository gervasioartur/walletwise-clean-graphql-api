package com.br.walletwise.usecase.user;

import com.br.walletwise.core.domain.entity.User;

import java.util.Optional;

public interface FindByEmail {
    Optional<User> find(String email);
}
