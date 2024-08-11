package com.br.walletwise.infra.service;

import com.br.walletwise.application.gateway.FindByUsernameGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.mappers.UserMapper;
import com.br.walletwise.infra.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindByUsernameGatewayImpl implements FindByUsernameGateway {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper mapper;

    @Override
    public Optional<User> find(String username) {
        return this.userJpaRepository.findByUsername(username).map(this.mapper::map);
    }
}