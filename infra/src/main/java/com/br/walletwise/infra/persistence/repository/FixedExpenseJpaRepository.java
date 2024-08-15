package com.br.walletwise.infra.persistence.repository;

import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface FixedExpenseJpaRepository extends JpaRepository<FixedExpenseJpaEntity, UUID> {

}