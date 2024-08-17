package com.br.walletwise.usecase.expense;

import com.br.walletwise.core.domain.entity.FixedExpense;

import java.util.Optional;
import java.util.UUID;

public interface GetFixedExpense {
    Optional<FixedExpense> get(UUID userId, long expenseCode);
}