package com.br.walletwise.application.gateway.expense;

import com.br.walletwise.core.domain.entity.FixedExpense;

import java.util.Optional;
import java.util.UUID;

public interface GetFixedExpenseGateway {
    Optional<FixedExpense> get(long expenseCode, UUID userId);
}