package com.br.walletwise.infra.persistence.repository;

import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FixedExpenseJpaRepository extends JpaRepository<FixedExpenseJpaEntity, UUID> {

}