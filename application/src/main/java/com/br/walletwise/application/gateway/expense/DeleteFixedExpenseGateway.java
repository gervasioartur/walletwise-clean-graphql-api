package com.br.walletwise.application.gateway.expense;

import com.br.walletwise.core.domain.entity.FixedExpense;


public interface DeleteFixedExpenseGateway {
    void delete(FixedExpense fixedExpense);
}
