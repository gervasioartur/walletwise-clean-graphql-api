package com.br.walletwise.infra.service;

import com.br.walletwise.application.gateway.FindByEmailGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.mappers.UserMapper;
import com.br.walletwise.infra.persistence.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindByEmailGatewayImpl implements FindByEmailGateway {
    private final IUserJpaRepository userJpaRepository;
    private final UserMapper mapper;

    @Override
    public Optional<User> find(String email) {
        return this.userJpaRepository.findByEmail(email).map(this.mapper::map);
    }
}
