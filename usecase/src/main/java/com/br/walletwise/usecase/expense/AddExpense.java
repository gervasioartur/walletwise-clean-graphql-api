package com.br.walletwise.usecase.expense;

import com.br.walletwise.core.domain.entity.Expense;

public interface AddExpense {
    Expense add(Expense expense);
}
