package com.br.walletwise.infra.service.user;

import com.br.walletwise.application.gateway.user.FindByEmailGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.mapper.UserMapper;
import com.br.walletwise.infra.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindByEmailGatewayImpl implements FindByEmailGateway {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper mapper;

    @Override
    public Optional<User> find(String email) {
        return this.userJpaRepository.findByEmail(email).map(this.mapper::map);
    }
}
