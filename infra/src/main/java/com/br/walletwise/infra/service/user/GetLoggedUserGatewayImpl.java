package com.br.walletwise.infra.service.user;

import com.br.walletwise.application.gateway.user.GetLoggedUserGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.infra.mappers.UserMapper;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import com.br.walletwise.infra.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLoggedUserGatewayImpl implements GetLoggedUserGateway {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper mapper;

    @Override
    public User get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserJpaEntity userJpaEntity = this.userJpaRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new NotFoundException("Username not found."));
        return this.mapper.map(userJpaEntity);
    }
}
