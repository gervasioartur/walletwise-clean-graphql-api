package com.br.walletwise.infra.persistence.repository;

import com.br.walletwise.infra.persistence.entity.SessionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionJpaRepository extends JpaRepository<SessionJpaEntity, UUID> {
}