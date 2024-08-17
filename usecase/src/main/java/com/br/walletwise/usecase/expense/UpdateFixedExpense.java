package com.br.walletwise.usecase.expense;

import com.br.walletwise.core.domain.model.FixedExpenseModel;

public interface UpdateFixedExpense {
    void update(FixedExpenseModel fixedExpense);
}
