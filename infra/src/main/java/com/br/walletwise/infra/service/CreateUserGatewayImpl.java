package com.br.walletwise.infra.service;

import com.br.walletwise.application.gateway.CreateUserGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.mappers.UserMapper;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import com.br.walletwise.infra.persistence.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateUserGatewayImpl implements CreateUserGateway {
    private final UserJpaRepository userRepository;
    private final UserMapper mapper;

    @Override
    public User create(User user) {
        UserJpaEntity entity = this.mapper.map(user);
        entity = this.userRepository.save(entity);
        return this.mapper.map(entity);
    }
}