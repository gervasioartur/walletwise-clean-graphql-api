package com.br.walletwise.usecase.expense;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.model.FixedExpenseModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetFixedExpense {
    Optional<FixedExpense> get(UUID userId, long expenseCode);
}