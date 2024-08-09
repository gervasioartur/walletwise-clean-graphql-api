package com.br.walletwise.infra.jpa.service;

import com.br.walletwise.application.gateway.FindByEmailGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.jpa.repository.IUserJpaRepository;
import com.br.walletwise.infra.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindByEmailJpaGatewayImpl implements FindByEmailGateway {
    private final IUserJpaRepository userJpaRepository;
    private final UserMapper mapper;

    @Override
    public Optional<User> find(String email) {
        return this.userJpaRepository.findByEmail(email).map(this.mapper::map);
    }
}
