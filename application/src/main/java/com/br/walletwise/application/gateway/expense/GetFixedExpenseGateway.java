package com.br.walletwise.application.gateway.expense;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.model.FixedExpenseModel;

import java.util.Optional;
import java.util.UUID;

public interface GetFixedExpenseGateway {
    Optional<FixedExpense> get(long expenseCode, UUID userId);

    Optional<FixedExpenseModel> getModel(long expenseCode, UUID userId);

}