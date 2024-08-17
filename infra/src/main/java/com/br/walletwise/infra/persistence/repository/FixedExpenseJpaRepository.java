package com.br.walletwise.infra.persistence.repository;

import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FixedExpenseJpaRepository extends JpaRepository<FixedExpenseJpaEntity, UUID> {
    @Query("SELECT fe FROM FixedExpenseJpaEntity fe WHERE fe.user.id = :userId AND fe.active = true")
    List<FixedExpenseJpaEntity> findByUserId(@Param("userId") UUID userId);

    @Query("SELECT fe FROM FixedExpenseJpaEntity fe WHERE fe.id = :id  AND fe.user.id = :userId AND fe.active = true")
    Optional<FixedExpenseJpaEntity> findByIdAndUserId(@Param("id") long id, @Param("userId") UUID userId);
}