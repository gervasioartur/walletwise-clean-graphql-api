package com.br.walletwise.application.gateway.expense;

import com.br.walletwise.core.domain.model.FixedExpenseModel;

import java.util.List;
import java.util.UUID;

public interface GetFixedExpensesGateway {
    List<FixedExpenseModel> get(UUID userId);
}
