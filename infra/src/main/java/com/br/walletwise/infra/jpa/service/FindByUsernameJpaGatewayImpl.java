package com.br.walletwise.infra.jpa.service;

import com.br.walletwise.application.gateway.FindByUsernameGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.jpa.repository.IUserJpaRepository;
import com.br.walletwise.infra.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindByUsernameJpaGatewayImpl implements FindByUsernameGateway {
    private final IUserJpaRepository userJpaRepository;
    private final UserMapper mapper;

    @Override
    public Optional<User> find(String username) {
        return this.userJpaRepository.findByUsername(username).map(this.mapper::map);
    }
}
