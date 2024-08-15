package com.br.walletwise.infra.helpers;

import com.br.walletwise.core.domain.model.GeneralEnumInt;
import com.br.walletwise.infra.persistence.entity.SessionJpaEntity;
import com.br.walletwise.infra.persistence.repository.SessionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValidateToken {
    private final GetUsernameFromToken getUsernameFromToken;
    private final SessionJpaRepository sessionJpaRepository;

    public boolean isValid(String token, UserDetails userDetails) {
        final String username = this.getUsernameFromToken.get(token);
        boolean isValid = false;
        if (username.equals(userDetails.getUsername())) {
            final Optional<SessionJpaEntity> entity = this.sessionJpaRepository.findByToken(token);
            if (entity.isPresent()) {
                if (LocalDateTime.now().isAfter(entity.get().getCreationDate().plusHours(GeneralEnumInt.JWT_TOKEN_EXPIRATION.getValue()))) {
                    entity.get().setActive(false);
                    this.sessionJpaRepository.save(entity.get());
                } else {
                    isValid = true;
                }
            }
        }
        return isValid;
    }
}
