package com.br.walletwise.infra.mappers;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.api.dto.CreateUserRequest;
import com.br.walletwise.infra.jpa.entity.UserJpaEntity;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapper {
    public User map(CreateUserRequest request){
        return new User(
                request.firstname(),
                request.lastname(),
                request.username(),
                request.email(),
                request.password());
    }


    public UserJpaEntity map(User user){
        return UserJpaEntity
                .builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.isActive())
                .build();
    }

    public User map(UserJpaEntity entity){
        return new User(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.isActive()
                );
    }
}
