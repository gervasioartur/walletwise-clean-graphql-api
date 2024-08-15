package com.br.walletwise.infra.persistence.repository;

import com.br.walletwise.infra.persistence.entity.SessionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SessionJpaRepository extends JpaRepository<SessionJpaEntity, UUID> {
    @Query("SELECT se FROM SessionJpaEntity  se WHERE se.token = :token AND se.active = true")
    Optional<SessionJpaEntity> findByToken(@Param("token") String token);
}