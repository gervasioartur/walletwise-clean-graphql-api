package com.br.walletwise.application.gateway.expense;

import com.br.walletwise.core.domain.entity.FixedExpense;

public interface AddFixedExpenseGateway {
    void add(FixedExpense fixedExpense);
}