package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.GetFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.usecase.expense.GetFixedExpense;

import java.util.Optional;
import java.util.UUID;

public class GetFixedExpenseImpl implements GetFixedExpense {
    private GetFixedExpenseGateway getFixedExpenseGateway;

    public GetFixedExpenseImpl(GetFixedExpenseGateway getFixedExpenseGateway) {
        this.getFixedExpenseGateway = getFixedExpenseGateway;
    }

    @Override
    public Optional<FixedExpense> get(UUID userId, long expenseCode) {
        return this.getFixedExpenseGateway.get(expenseCode, userId);
    }
}
