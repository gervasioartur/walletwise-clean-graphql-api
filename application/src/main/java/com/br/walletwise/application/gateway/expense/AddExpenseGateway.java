package com.br.walletwise.application.gateway.expense;

import com.br.walletwise.core.domain.entity.Expense;

public interface AddExpenseGateway {
    Expense addExpense(Expense expense);
}
