package com.br.walletwise.infra.jpa.repository;

import com.br.walletwise.infra.jpa.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IUserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    @Query("SELECT user FROM UserJpaEntity  user WHERE  user.username = :username AND user.active = true")
    Optional<UserJpaEntity> findByUsername(@Param("username") String username);

    @Query("SELECT user FROM UserJpaEntity user WHERE user.email = :email  AND user.active = true")
    Optional<UserJpaEntity> findByEmail(@Param("email") String email);
}